package com.ask.vkparsenews

import android.app.Application
import com.ask.vkparsenews.di.AppComponent
import com.ask.vkparsenews.di.AppModule
import com.ask.vkparsenews.di.DaggerAppComponent


class App:Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}