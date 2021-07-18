package com.example.navertranslator.data.model

data class Message(
    val service: String,
    val type: String,
    val version: String,
    val result: ResultX
)