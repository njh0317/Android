package com.example.navernewsapp.data.model

import com.google.gson.annotations.SerializedName

data class SearchItem(
    @SerializedName("display") val display: Int,
    @SerializedName("items") val items: List<Items>,
    @SerializedName("lastBuildDate") val lastBuildDate: String,
    @SerializedName("start") val start: Int,
    @SerializedName("total") val total: Int
)