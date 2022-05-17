package com.ask.vkparsenews.domain

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ask.vkparsenews.data.database.AppDatabase
import com.ask.vkparsenews.data.model.EventsModel
import com.ask.vkparsenews.data.paging.EventsRemoteMediator
import com.ask.vkparsenews.domain.entities.CurrentTime
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainInteractorImp @Inject constructor(
    private val repo: Repository, private val currentTime: CurrentTime, private val db: AppDatabase
) : MainInteractor {

    override suspend fun initFlow(
        startData: String,
        endData: String,
    ): Flow<PagingData<EventsModel>> {
        val startDateTime = currentTime.convertDateToMillisSec(startData)
        val endDateTime = currentTime.convertDateToMillisSec(endData)

        @OptIn(ExperimentalPagingApi::class)
        val flow = Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                initialLoadSize = 3,
                prefetchDistance = 10,
                maxSize = 300
            ), remoteMediator = EventsRemoteMediator(repo, db, Pair(startDateTime, endDateTime))
        ) {
            db.eventsDao().pagingSource()
        }.flow
        return flow
    }

    override fun initTimeView(timeStamp: Long): String =
         currentTime.convertTimeMillisToStringDate(timeStamp)

}