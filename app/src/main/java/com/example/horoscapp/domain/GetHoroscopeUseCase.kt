package com.example.horoscapp.domain

import com.example.horoscapp.core.network.ResultType
import com.example.horoscapp.data.network.HoroscopeRepository
import com.example.horoscapp.domain.dto.HoroscopeDTO
import com.example.horoscapp.domain.model.HoroscopeModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHoroscopeUseCase @Inject constructor(private val horoscopeRepository: HoroscopeRepository) {
    operator fun invoke(horoscopeDTO: HoroscopeDTO): Flow<ResultType<HoroscopeModel>> =
        horoscopeRepository.getHoroscope(horoscopeDTO)

}