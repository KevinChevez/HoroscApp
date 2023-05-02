package com.example.horoscapp.ui.detail

import com.example.horoscapp.domain.model.HoroscopeModel

sealed class DetailUIState{
    object Loading: DetailUIState()
    data class Success(val horoscopeModel: HoroscopeModel): DetailUIState()
    data class Error(val msg: String): DetailUIState()
}
