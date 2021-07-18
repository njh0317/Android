package com.example.navertranslator.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.navertranslator.data.repository.TranslateRequestFactory

class MainViewModelFactory(private val repository: TranslateRequestFactory): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}