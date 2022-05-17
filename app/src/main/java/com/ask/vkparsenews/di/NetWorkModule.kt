package com.ask.vkparsenews.di

import com.ask.vkparsenews.data.model.Events
import com.ask.vkparsenews.data.network.ApiService
import com.ask.vkparsenews.data.network.AuthInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetWorkModule {

    @Singleton
    companion object {
        private const val API_TOKEN_NAME_FIELD = "access_token"
       private const val BASE_URL = "https://api.vk.com/method/"
    }



    @Provides
    @Singleton
     fun getService(retrofit:Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
     fun retrofit(authInterceptor:AuthInterceptor): Retrofit {

        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }

    @Provides
    @Singleton
    fun getClient(): AuthInterceptor = AuthInterceptor()

}