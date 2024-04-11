package com.abdoali.newsapp.date.api

import com.abdoali.newsapp.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/v2/everything")
    suspend fun search(
        @Query("apiKey") apiKey: String = BuildConfig.api_Key,
        @Query("q") query: String = "",
        @Query("pageSize") pageSize: Int =10,
        @Query("page") page: Int ,
    ): String


}