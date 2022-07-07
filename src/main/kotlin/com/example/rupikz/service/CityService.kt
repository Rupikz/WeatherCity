package com.example.rupikz.service

import com.example.rupikz.common.exception.CityNotFoundException
import com.example.rupikz.dto.CityCreateDto
import com.example.rupikz.repository.CityRepository
import com.example.rupikz.entity.CityEntity
import com.example.rupikz.enum.CityType
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CityService(
    private val cityRepository: CityRepository
) {

    fun find(): List<CityEntity> = cityRepository.findAll()

    fun findOne(id: UUID): CityEntity {
        if (!cityRepository.existsById(id)) throw CityNotFoundException()
        return cityRepository.findById(id).get()
    }

    fun create(cityCreateDto: CityCreateDto): CityEntity {
        return CityEntity().apply {
            this.name = cityCreateDto.name
            this.type = CityType.LARGE // TODO: брать из дто
            this.description = cityCreateDto.description
            cityRepository.save(this)
        }
    }

    fun delete(id: UUID) {
        if (!cityRepository.existsById(id)) throw CityNotFoundException()
        cityRepository.deleteById(id)
    }

}
