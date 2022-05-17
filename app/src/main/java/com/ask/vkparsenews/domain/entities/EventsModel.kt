package com.ask.vkparsenews.domain.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
//вынести в слой домена
@Entity(tableName = "events_model")
@Parcelize
data class EventsModel(
    @field:PrimaryKey(autoGenerate = true)  val id:Long = 0,
    val idEv:Long,
    val title:String,
    val data:String,
    val typeEvent:String,
    val url:String?,
    val descript: String?,
    val next_page:String
) : Parcelable
