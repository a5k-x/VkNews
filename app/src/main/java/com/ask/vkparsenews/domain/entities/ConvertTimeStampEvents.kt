package com.ask.vkparsenews.domain.entities

import com.ask.vkparsenews.data.model.Events
import com.ask.vkparsenews.data.model.EventsModel

interface ConvertTimeStampEvents {
    suspend fun convertEventToEventModel(list: Events?):List<EventsModel>
}