package com.ask.vkparsenews.domain

import androidx.paging.PagingData
import com.ask.vkparsenews.data.model.EventsModel
import kotlinx.coroutines.flow.Flow

interface MainInteractor {

   //  suspend fun getListEvents(startData: String, endData: String, nextPage:String?): List<EventsModel>?

     suspend fun initFlow(startData: String, endData: String): Flow<PagingData<EventsModel>>

     fun initTimeView(timeStamp: Long): String
}