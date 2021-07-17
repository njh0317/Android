package com.example.navernewsapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.navernewsapp.data.model.Items
import com.example.navernewsapp.data.repository.NewsRequestRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainViewModel(private val repository:NewsRequestRepository): ViewModel() {

    private val _data = MutableLiveData<List<Items>>()
    val data: LiveData<List<Items>> get() = _data

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> get() = _dataLoading


    val clientId = "zS1qDEecUmvOwH4z7q4a"
    val clientKey = "O2M_UWko7J"

    fun getSearchResult(keyword: String) {
        _dataLoading.value = true
        CoroutineScope(Dispatchers.IO).launch {
            repository.requestItemForCoroutine(clientId, clientKey, keyword, 10, 1, "sim")?.let {
                _data.postValue(it.items)
                _dataLoading.postValue(false)
            }
        }
    }
}