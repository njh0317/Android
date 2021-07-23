package com.example.dagger

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dagger.di.RetroService
import com.example.dagger.model.RecyclerList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivityViewModel(application: Application): AndroidViewModel(application){
    @Inject
    lateinit var mService: RetroService

    private lateinit var liveDataList: MutableLiveData<RecyclerList>

    init {
        (application as MyApplication).getRetroComponent().inject(this)
        liveDataList = MutableLiveData()
    }

    fun getLiveDataObserver():MutableLiveData<RecyclerList> {
        return liveDataList
    }

    fun makeApicall() {
        val call: Call<RecyclerList>? =  mService.getDataFromAPI("atl")

        call?.enqueue(object: Callback<RecyclerList>{
            override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {
                if(response.isSuccessful) {
                    liveDataList.postValue(response.body())

                }

            }

            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {

            }

        })
    }

}