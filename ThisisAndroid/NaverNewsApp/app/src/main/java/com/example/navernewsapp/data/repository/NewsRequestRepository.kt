package com.example.navernewsapp.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRequestRepository {

    suspend fun requestItemForCoroutine(clientId: String, clientSecret: String, query: String, display: Int, start: Int, sort: String) = withContext(
        Dispatchers.IO)
    {
        ApiRequestFactory.retrofit.getSearchItems(clientId, clientSecret, query, display, start, sort)
    }
}