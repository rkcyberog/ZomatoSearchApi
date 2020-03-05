package com.rahul.gamechangeassignment.data.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rahul.gamechangeassignment.data.SearchResponse
import com.rahul.gamechangeassignment.data.remote.SearchApi
import com.rahul.gamechangeassignment.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SearchRepository @Inject constructor(val searchApi: SearchApi, val app: Application, val utils: Utils
) {
   // var shouldFetch: Boolean=false
   private val isLoading: MutableLiveData<Int> = MutableLiveData<Int>()


    fun searchApi(queryParameters1:String,queryParams2:String,queryParams3:String): LiveData<SearchResponse> {
        isLoading.setValue(1);
        val data = MutableLiveData<SearchResponse>()
        searchApi.getsearchApi(queryParameters1,queryParams2,queryParams3).enqueue(object :
            Callback<SearchResponse> {

            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                isLoading.setValue(2);
                data.setValue(response.body())
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.e("error", t.message)
                isLoading.setValue(3);
                data.setValue(null)
            }
        })

        return data
    }

    fun getIsLoading(): LiveData<Int> {
        return isLoading
    }

}