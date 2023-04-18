package com.example.horoscapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horoscapp.domain.GetHoroscopeUseCase
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
            getHoroscopeUseCase().collect {
                if(it != null){
                    // Here is the information needed
                    _uiState.value = DetailUIState.Success(it)
                } else {
                    _uiState.value = DetailUIState.Error("There was an error getting the Horoscope")
                }
            }
        }
    }
}