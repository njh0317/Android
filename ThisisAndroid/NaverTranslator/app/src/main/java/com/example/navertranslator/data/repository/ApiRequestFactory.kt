package com.example.navertranslator.data.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRequestFactory {
    private const val baseUrl = "https://openapi.naver.com/v1/"

    val retrofit: Service = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Service::class.java)
}