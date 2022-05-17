package com.ask.vkparsenews.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ask.vkparsenews.data.database.dao.EventsDao
import com.ask.vkparsenews.data.database.dao.PageKeyDao
import com.ask.vkparsenews.data.model.PageKey
import com.ask.vkparsenews.domain.entities.EventsModel

@Database(entities = [EventsModel::class, PageKey::class], version = 1, exportSchema = true)
abstract class AppDatabase:RoomDatabase() {
    abstract fun eventsDao(): EventsDao
    abstract fun pageKeyDao(): PageKeyDao

    companion object {
        @Volatile private var instance: AppDatabase? = null
        private const val NAME_DATABASE = "vknews"

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, NAME_DATABASE)
                .fallbackToDestructiveMigration()
                .build()

    }


}