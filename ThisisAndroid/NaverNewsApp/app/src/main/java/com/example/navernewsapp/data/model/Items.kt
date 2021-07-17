package com.example.navernewsapp.data.model

import com.google.gson.annotations.SerializedName

data class Items(
    @SerializedName("description") val description: String,
    @SerializedName("link") val link: String,
    @SerializedName("originallink") val originallink: String,
    @SerializedName("pubDate") val pubDate: String,
    @SerializedName("title") val title: String
)