package com.example.horoscapp.domain

import com.example.horoscapp.data.network.HoroscopeApi
import com.example.horoscapp.data.network.model.HoroscopeResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetHoroscopeUseCase @Inject constructor(private val api: HoroscopeApi) {

    suspend operator fun invoke(): Flow<HoroscopeResponse?>{
        val response = api.getHoroscope("", "")
        if (response.isSuccessful){
            return flowOf(response.body())
        } else {
            return flowOf(null)
        }
    }
}