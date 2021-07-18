package com.example.navertranslator.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.Header
import retrofit2.http.Query

class TranslateRequestFactory {

    suspend fun requestTranslateForCoroutine(clientId: String, clientSecret: String, source: String, target: String, text: String) =
        withContext(Dispatchers.IO){
            ApiRequestFactory.retrofit.translate(clientId, clientSecret, source, target, text)
    }
}