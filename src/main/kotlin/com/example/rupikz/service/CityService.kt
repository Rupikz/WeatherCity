package com.example.rupikz.service

import com.example.rupikz.common.exception.CityAlreadyExistException
import com.example.rupikz.common.exception.CityNotFoundException
import com.example.rupikz.dto.CityCreateDto
import com.example.rupikz.repository.CityRepository
import com.example.rupikz.entity.CityEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CityService(
    private val cityRepository: CityRepository
) {

    fun findAll(pageable: Pageable): Page<CityEntity> = cityRepository.findAll(pageable)

    fun findOne(id: UUID): CityEntity {
        if (!cityRepository.existsById(id)) throw CityNotFoundException()
        return cityRepository.findById(id).get()
    }

    fun create(cityCreateDto: CityCreateDto): CityEntity {
        if (cityRepository.existsByName(cityCreateDto.name)) throw CityAlreadyExistException()
        return CityEntity().apply {
            this.name = cityCreateDto.name
            this.type = cityCreateDto.type
            this.description = cityCreateDto.description
            cityRepository.save(this)
        }
    }

    fun delete(id: UUID) {
        if (!cityRepository.existsById(id)) throw CityNotFoundException()
        cityRepository.deleteById(id)
    }

}
