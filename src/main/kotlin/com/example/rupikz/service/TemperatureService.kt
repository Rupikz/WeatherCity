package com.example.rupikz.service

import com.example.rupikz.common.exception.CityNotFoundException
import com.example.rupikz.common.exception.TemperatureAlreadyExistException
import com.example.rupikz.common.exception.TemperatureNotFoundException
import com.example.rupikz.dto.TemperatureCreateDto
import com.example.rupikz.entity.TemperatureEntity
import com.example.rupikz.repository.CityRepository
import com.example.rupikz.repository.TemperatureRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TemperatureService(
    private val temperatureRepository: TemperatureRepository,
    private val cityRepository: CityRepository
) {

    fun findAll(pageable: Pageable): Page<TemperatureEntity> = temperatureRepository.findAll(pageable)

    fun create(temperatureCreateDto: TemperatureCreateDto): TemperatureEntity {
        if (temperatureRepository.existsByDate(temperatureCreateDto.date)) throw TemperatureAlreadyExistException()
        if (!cityRepository.existsById(temperatureCreateDto.cityId)) throw CityNotFoundException()
        return temperatureRepository.save(TemperatureEntity( // TODO: пофиксить циклический джоин
            date = temperatureCreateDto.date,
            celsius = temperatureCreateDto.celsius,
            city = cityRepository.findById(temperatureCreateDto.cityId).get()
        ))
    }

    fun delete(id: UUID) {
        if (!temperatureRepository.existsById(id)) throw TemperatureNotFoundException()
        temperatureRepository.deleteById(id)
    }

}
