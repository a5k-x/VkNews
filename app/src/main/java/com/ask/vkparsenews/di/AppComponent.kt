package com.ask.vkparsenews.di

import com.ask.vkparsenews.presentation.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,DbModule::class,NetWorkModule::class])
interface AppComponent {

    fun inject(mainViewModel: MainViewModel)

}