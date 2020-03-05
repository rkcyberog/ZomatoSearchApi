package com.rahul.gamechangeassignment.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.rahul.gamechangeassignment.data.models.Restaurants

data class SearchResponse(

    @SerializedName("results_shown")
    @Expose
    private var resultsShown: Int? = null,

    @SerializedName("restaurants")
    @Expose
    val restaurants:List<Restaurants>? = null

)
