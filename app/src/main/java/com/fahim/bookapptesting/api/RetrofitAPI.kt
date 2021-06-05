package com.fahim.bookapptesting.api

import com.fahim.bookapptesting.model.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPI {

    @GET("/api/")
    suspend fun getImages(
        @Query("q") q: String,
        @Query("key") key: String
    ): Response<ImageResponse>
}