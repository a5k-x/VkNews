package com.ask.vkparsenews.domain.entities

import com.ask.vkparsenews.data.model.Events
import com.ask.vkparsenews.data.model.EventsModel
import java.text.SimpleDateFormat
import java.util.*


class ConvertTimeStampEventsImp:ConvertTimeStampEvents {

    companion object{
        private const val TIME_PATTERN = "dd.MM.yyyy HH:mm"
        private const val UNKNOWN_AUTHOR = "Unknown author"
        private const val PREFIX_DELETE = "-"
    }

    private val formatDate = SimpleDateFormat(TIME_PATTERN)
    private val listEventsModel = mutableListOf<EventsModel>()

    override suspend fun convertEventToEventModel(list: Events?): List<EventsModel> {
      listEventsModel.clear()
         list?.response?.items?.forEach {
           val data = it.date
           val dataNew = conversionData(data)
           val nameAuthor = getAuthorEvent(it.from_id,list)
           val eventsModel = EventsModel(idEv = it.id, title = nameAuthor, data = dataNew,
               typeEvent = it.post_type, url = it.attachments?.first()?.photo?.sizes?.last()?.url,
               descript = it.text, next_page = list.response.next_from)
           listEventsModel.add(eventsModel)
       }
        return listEventsModel
    }

    private fun getAuthorEvent(idAuthor: String, list: Events?): String {
        val idAuthor = idAuthor.removePrefix(PREFIX_DELETE)
        val profiles = list?.response?.profiles?.filter { it.id == idAuthor }
        val groups = list?.response?.groups?.filter { it.id == idAuthor }
        return if (profiles?.isNotEmpty() == true) {
            profiles.first().first_name + " " + profiles.first().last_name
        } else if (groups?.isNotEmpty() == true) {
            groups.first().name
        } else UNKNOWN_AUTHOR
    }

    private fun conversionData(time: Long): String {
        val date = Date(time * 1000)
        return formatDate.format(date)
    }
}