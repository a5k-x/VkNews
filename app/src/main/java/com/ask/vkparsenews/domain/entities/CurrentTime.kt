package com.ask.vkparsenews.domain.entities

interface CurrentTime {

      fun convertDateToMillisSec(currentTime:String):Long

      fun convertTimeMillisToStringDate(timestamp:Long):String
}