package com.ask.vkparsenews.data.network

import android.util.Log
import com.ask.vkparsenews.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor() : Interceptor {

    companion object {
        private const val API_TOKEN = "access_token"
        private const val API_VERSION = "v"
        private const val EXTENDED = "extended"
        private const val API_VERSION_V = "5.131"
        private const val EXTENDED_VALUE = "true"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newHttpUrl = request.url.newBuilder()
            .addQueryParameter(API_TOKEN, BuildConfig.APIKEY)
            .addQueryParameter(API_VERSION, API_VERSION_V)
            .addQueryParameter(EXTENDED, EXTENDED_VALUE)

            .build()
        val requestBuilder = request.newBuilder()
            .url(newHttpUrl)
            .build()
       val url = requestBuilder.url
        Log.i("AAA","$url")
        return chain.proceed(requestBuilder)
    }
}