package com.rahul.gamechangeassignment.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rahul.gamechangeassignment.data.SearchResponse
import com.rahul.gamechangeassignment.data.repository.SearchRepository
import javax.inject.Inject


class SearchViewModel  @Inject constructor(
    private val searchRepository: SearchRepository) : ViewModel() {

    fun getObserverableSearch(lattitude: String, longitude: String, text: String): LiveData<SearchResponse> {
        return searchRepository.searchApi(lattitude,longitude,text) as MutableLiveData<SearchResponse>;
    }

    fun getObservableIsLoading(): LiveData<Int> {
        return searchRepository.getIsLoading()
    }

}