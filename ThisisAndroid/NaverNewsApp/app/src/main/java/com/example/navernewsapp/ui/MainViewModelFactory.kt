package com.example.navernewsapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.navernewsapp.data.repository.NewsRequestRepository

class MainViewModelFactory(private val repository: NewsRequestRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T

    }
}