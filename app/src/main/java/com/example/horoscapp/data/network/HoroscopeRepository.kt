package com.example.horoscapp.data.network

import com.example.horoscapp.core.network.ResultType
import com.example.horoscapp.data.network.model.HoroscopeResponse
import com.example.horoscapp.data.network.model.toDomain
import com.example.horoscapp.domain.dto.HoroscopeDTO
import com.example.horoscapp.domain.model.HoroscopeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class HoroscopeRepository @Inject constructor(private val api: HoroscopeApi) {

    fun getHoroscope(horoscopeDTO: HoroscopeDTO): Flow<ResultType<HoroscopeModel>> = flow {
        try {
            val response = api.getHoroscope(horoscopeDTO.sign, horoscopeDTO.date, horoscopeDTO.lang)
            if(response.isSuccessful){
                response.body()?.let {horoscopeResponse ->
                    emit(ResultType.Success(horoscopeResponse.toDomain()))
                }
            } else {
                val msg = when(response.code()){
                    404 -> "Not found error"
                    400 -> "Internal error server"
                    else -> "Generic error"
                }
                emit(ResultType.Error(msg))
            }
        } catch (e: Exception){
            val msg = when(e){
                is IOException -> "IO Error"
                else -> "Other error: "+e.message
            }
            emit(ResultType.Error(msg))
        }
    }

}