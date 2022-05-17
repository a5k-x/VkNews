package com.ask.vkparsenews.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ask.vkparsenews.domain.entities.EventsModel

@Dao
interface EventsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(events: List<EventsModel>)

    @Query("SELECT * FROM events_model")
    fun pagingSource(): PagingSource<Int, EventsModel>

    @Query("DELETE FROM events_model")
    suspend fun clearAll()
}