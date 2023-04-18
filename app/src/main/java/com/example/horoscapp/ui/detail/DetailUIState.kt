package com.example.horoscapp.ui.detail

import com.example.horoscapp.data.network.model.HoroscopeResponse

sealed class DetailUIState{
    object Loading: DetailUIState()
    data class Success(val horoscopeResponse: HoroscopeResponse): DetailUIState()
    data class Error(val msg: String): DetailUIState()
}
