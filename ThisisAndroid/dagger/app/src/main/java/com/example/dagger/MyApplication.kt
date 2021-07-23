package com.example.dagger

import android.app.Application
import com.example.dagger.di.DaggerRetroComponent
import com.example.dagger.di.RetroComponent
import com.example.dagger.di.RetroModule

class MyApplication: Application() {

    private lateinit var retroComponent: RetroComponent
    override fun onCreate() {
        super.onCreate()
        retroComponent = DaggerRetroComponent.builder()
            .retroModule(RetroModule())
            .build()
    }

    fun getRetroComponent(): RetroComponent {
        return retroComponent
    }
}