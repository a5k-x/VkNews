package com.ask.vkparsenews.domain.entities

import java.text.SimpleDateFormat
import java.util.*

class CurrentTimeImp : CurrentTime {

    private val formatDate = SimpleDateFormat(TIME_PATTERN)

    override fun convertDateToMillisSec(currentTime:String):Long =
       if (currentTime.isNullOrEmpty()) {
           try {
               formatDate.parse(currentTime).time.div(1000)
           } catch (e: Exception){
               0
           }
       } else 0


    override fun convertTimeMillisToStringDate(timestamp: Long): String {
        val date = Date(timestamp)
        return formatDate.format(date)
    }

    companion object {
        private const val TIME_PATTERN = "dd:MM:yyyy"
    }
}