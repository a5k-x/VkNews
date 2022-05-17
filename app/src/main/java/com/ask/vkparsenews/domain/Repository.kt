package com.ask.vkparsenews.domain

import com.ask.vkparsenews.domain.entities.EventsModel


interface Repository {

    suspend fun getListEvents(startData: Long, endData: Long, nextPage: String?): List<EventsModel>?

}