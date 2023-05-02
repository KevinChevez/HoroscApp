package com.example.horoscapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horoscapp.core.network.ResultType
import com.example.horoscapp.domain.GetHoroscopeUseCase
import com.example.horoscapp.domain.dto.HoroscopeDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val getHoroscopeUseCase: GetHoroscopeUseCase): ViewModel() {

    private val  _uiState = MutableStateFlow<DetailUIState>(DetailUIState.Loading)
    val uiState: StateFlow<DetailUIState> = _uiState

    fun getHoroscope() {
        viewModelScope.launch {
            getHoroscopeUseCase(HoroscopeDTO(sign="aries")).collect {
                when(it){
                    is ResultType.Error -> {
                        _uiState.value = DetailUIState.Error(it.msg ?:"Default error (DetailViewModel).")
                    }
                    is ResultType.Success -> {
                        _uiState.value = DetailUIState.Success(it.data!!)
                    }
                }
            }
        }
    }
}