package com.ur.unitedrtest.Retrofit

import com.ur.unitedrtest.Data.Items
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface Api {

    @GET("repositories")
    @Headers("Content-Type:application/json")
    fun getrepos(
        @Query(encoded = true, value = "q") date: String, @Query(value = "page") page: Int, @Query("sort") sort: String, @Query("order") order: String
    ): Call<Items>

}