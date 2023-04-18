package com.example.horoscapp.data.network

import com.example.horoscapp.data.network.model.HoroscopeResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface HoroscopeApi {

    @POST(".")
    suspend fun getHoroscope(
        @Query("sign") sign: String,
        @Query("dat") day: String,
    ): Response<HoroscopeResponse>
}