package com.ask.vkparsenews.data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.ask.vkparsenews.data.database.AppDatabase
import com.ask.vkparsenews.data.model.PageKey
import com.ask.vkparsenews.domain.Repository
import com.ask.vkparsenews.domain.entities.EventsModel

@OptIn(ExperimentalPagingApi::class)
class EventsRemoteMediator(
    val repository: Repository,
    private val db: AppDatabase,
    private val pairTime: Pair<Long, Long>
) :
    RemoteMediator<Int, EventsModel>() {
    private val eventsDao = db.eventsDao()
    private val pageKeyDao = db.pageKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, EventsModel>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {

                LoadType.REFRESH -> null

                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    val remoteKey: PageKey? = db.withTransaction {
                        if (lastItem?.idEv != null) {
                            pageKeyDao.getNextPageKey()?.last()
                        } else null
                    }
                    if (remoteKey?.nextPageUrl == null) {
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    } else {
                        remoteKey.nextPageUrl
                    }
                }
            }

            val resBody = repository.getListEvents(pairTime.first, pairTime.second, nextPage = loadKey)

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    eventsDao.clearAll()
                    pageKeyDao.clearAll()
                }
                val lastPageKey = resBody?.last()?.next_page
                pageKeyDao.insertOrReplace(PageKey(nextPageUrl = lastPageKey))
                if (resBody != null) {
                    eventsDao.insertAll(resBody)
                }
            }
            MediatorResult.Success(endOfPaginationReached = resBody?.last()?.next_page == null)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}