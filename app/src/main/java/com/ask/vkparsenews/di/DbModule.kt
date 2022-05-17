package com.ask.vkparsenews.di

import com.ask.vkparsenews.App
import com.ask.vkparsenews.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {
    @Singleton
    @Provides
    fun provideDatabase(app: App):AppDatabase = AppDatabase.getDatabase(app.applicationContext)
}
