package com.rahul.gamechangeassignment.data.remote

import com.rahul.gamechangeassignment.data.SearchResponse
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import java.util.*

interface SearchApi {

   @Headers("user_key: 231ad2fb5d5e7f4fa49d9c90697dd3d4")
   @GET("api/v2.1/search")
   fun getsearchApi(@Query("lat") queryParameters1: String,
                 @Query("lon")queryParams2: String,
                 @Query("q") queryParams3: String): Call<SearchResponse>
}