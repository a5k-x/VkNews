package com.ask.vkparsenews.data.model

data class Events(
    val response: EventTest
)

data class EventTest(
    val items: List<Event>,
    val profiles: List<Profiles>?,
    val groups: List<Groups>?,
    val next_from: String

)

data class Event(
    val id: Long,
    var date: Long,
    val text: String,
    val post_type:String,
    val from_id: String,
    val attachments: List<Attachments>?,

)

data class Profiles(
    val id:String,
    val first_name:String,
    val last_name:String,

)

data class Groups(
    val id:String,
    val name:String,

)

data class Attachments(
    val photo: Photo
)

data class Photo(
    val sizes:List<PhotoElements>
)

data class PhotoElements(
    val url:String
)
