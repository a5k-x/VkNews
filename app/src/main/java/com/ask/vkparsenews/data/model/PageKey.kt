package com.ask.vkparsenews.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "page_key")
@Parcelize
data class PageKey(
    @field:PrimaryKey(autoGenerate = true) val id: Long=0,
    val nextPageUrl: String?
) : Parcelable