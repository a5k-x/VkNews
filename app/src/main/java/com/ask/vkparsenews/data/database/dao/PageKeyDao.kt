package com.ask.vkparsenews.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ask.vkparsenews.data.model.PageKey

@Dao
interface PageKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(pageKey: PageKey)

    @Query("SELECT * FROM page_key ")
    fun getNextPageKey(): List<PageKey>?

    @Query("DELETE FROM page_key")
    suspend fun clearAll()
}