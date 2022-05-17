package com.ask.vkparsenews.data.network

import com.ask.vkparsenews.data.model.Events
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("newsfeed.search")
    suspend fun getListEvents(
        @Query("q") find: String = "Ижевск",
        @Query("count") count: Int = 20,
        @Query("start_time") startTime: Long,
        @Query("end_time") endTime: Long,
        @Query("start_from") keyNextPage:String?,
        ): Events?
}