package com.ask.vkparsenews.data.repository

import com.ask.vkparsenews.data.network.ApiService
import com.ask.vkparsenews.domain.Repository
import com.ask.vkparsenews.domain.entities.ConvertTimeStampEvents
import com.ask.vkparsenews.domain.entities.EventsModel
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val retrofit: ApiService,
    private var conterterModel: ConvertTimeStampEvents
) : Repository {

    override suspend fun getListEvents(
        startData: Long,
        endData: Long,
        nextPage: String?
    ): List<EventsModel>? {
        val events =
            retrofit.getListEvents(startTime = startData, endTime = endData, keyNextPage = nextPage)
        return events?.let { conterterModel.convertEventToEventModel(it) }
    }
}