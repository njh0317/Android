package com.example.navernewsapp.data.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRequestFactory {
    private const val baseUrl = "https://openapi.naver.com/v1/"

    val retrofit: Service = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        /*.client(OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().apply { this.level = HttpLoggingInterceptor.Level.BODY }
        ).build()) for logging */
        .build()
        .create(Service::class.java)
}