package com.ask.vkparsenews.domain

import androidx.paging.PagingData
import com.ask.vkparsenews.domain.entities.EventsModel

import kotlinx.coroutines.flow.Flow

interface MainInteractor {

     suspend fun initFlow(startData: String, endData: String): Flow<PagingData<EventsModel>>

     fun initTimeView(timeStamp: Long): String
}