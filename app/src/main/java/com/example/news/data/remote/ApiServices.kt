package com.example.news.data.remote

import com.example.news.model.news.NewsResponse
import com.example.news.model.sources.Response
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiServices {
    @GET("sources")
    fun getNewsSources(
        @Query("apiKey") apiKey : String,
        @Query("language") language : String,
        @Query("country") country : String,
        @Query("category") category : String
    ): Call<Response>

    @GET("everything")
    fun getSourceNews(
        @Query("sources") sourceId : String,
        @Query("apiKey") apiKey : String

    ): Call<NewsResponse>

}

