package com.example.navertranslator.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.navertranslator.data.repository.TranslateRequestFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: TranslateRequestFactory): ViewModel() {
    private val _data = MutableLiveData<String>()
    val data: LiveData<String> get() = _data

    val clientId = "98NvpE0fqJYSVcvGPVDL"
    val clientKey = "hK72l8uTxa"

    fun translate(keyword: String) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.requestTranslateForCoroutine(clientId, clientKey, "ko", "en", keyword).let {
                _data.postValue(it.message.result.translatedText)
            }
        }
    }
}