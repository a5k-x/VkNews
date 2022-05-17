package com.ask.vkparsenews.di

import com.ask.vkparsenews.App
import com.ask.vkparsenews.data.database.AppDatabase
import com.ask.vkparsenews.data.model.Events
import com.ask.vkparsenews.data.network.ApiService
import com.ask.vkparsenews.data.repository.RepositoryImp
import com.ask.vkparsenews.domain.MainInteractor
import com.ask.vkparsenews.domain.MainInteractorImp
import com.ask.vkparsenews.domain.Repository
import com.ask.vkparsenews.domain.entities.ConvertTimeStampEvents
import com.ask.vkparsenews.domain.entities.ConvertTimeStampEventsImp
import com.ask.vkparsenews.domain.entities.CurrentTime
import com.ask.vkparsenews.domain.entities.CurrentTimeImp
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {
    @Provides
    fun app(): App {
        return app
    }

    @Singleton
    @Provides
    fun conterterModel(): ConvertTimeStampEvents = ConvertTimeStampEventsImp()

    @Singleton
    @Provides
    fun repositoryImp(
        apiService: ApiService,
        conterterModel: ConvertTimeStampEvents
    ): Repository = RepositoryImp(apiService, conterterModel)

    @Singleton
    @Provides
    fun currentTime(): CurrentTime = CurrentTimeImp()

    @Singleton
    @Provides
    fun interactor(repository:Repository, currentTime:CurrentTime,db: AppDatabase): MainInteractor = MainInteractorImp(repository,currentTime,db)
}