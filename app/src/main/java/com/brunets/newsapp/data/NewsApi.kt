package com.brunets.newsapp.data

import com.brunets.newsapp.data.model.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("/v2/everything")
    suspend fun getNews(@Query("q")company: String, @Query("pageSize") pageSize: String, @Query("page")page: String): Response<News>
}