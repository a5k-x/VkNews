package com.ask.vkparsenews.domain.entities

import com.ask.vkparsenews.data.model.Events


interface ConvertTimeStampEvents {

    suspend fun convertEventToEventModel(list: Events?):List<EventsModel>

}