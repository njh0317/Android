package com.example.navertranslator.data.repository

import com.example.navertranslator.data.model.Message
import com.example.navertranslator.data.model.ResultX
import com.example.navertranslator.data.model.result
import retrofit2.http.*

interface Service {

    @FormUrlEncoded
    @POST("papago/n2mt")
    suspend fun translate(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientSecret: String,
        @Field("source") source: String,
        @Field("target") target: String,
        @Field("text") text: String
    ) : result
}
