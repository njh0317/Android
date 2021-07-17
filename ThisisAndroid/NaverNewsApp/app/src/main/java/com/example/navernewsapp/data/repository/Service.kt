package com.example.navernewsapp.data.repository

import com.example.navernewsapp.data.model.SearchItem
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Service {
    @GET("search/news.json")
    suspend fun getSearchItems(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientSecret: String,
        @Query("query") query: String,
        @Query("display") display: Int,
        @Query("start") start: Int,
        @Query("sort") sort: String ): SearchItem

}