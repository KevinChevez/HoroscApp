package com.example.horoscapp.core.network

import com.example.horoscapp.data.network.model.HoroscopeResponse

sealed class ResultType<T>{
//    val response = ResultType.Success<HoroscopeResponse>()
    data class Success<T>(val data: T?): ResultType<T>()

//    val response = ResultType.Error<HoroscopeResponse>("message")
    data class Error<T>(val msg: String?): ResultType<T>()
}
