package com.example.rupikz.controller

import com.example.rupikz.common.validation.ValidUUID
import com.example.rupikz.dto.CityCreateDto
import org.springframework.web.bind.annotation.*
import com.example.rupikz.service.CityService
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import java.util.UUID
import javax.validation.Valid
import com.example.rupikz.entity.CityEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import javax.validation.constraints.NotNull

@Validated
@RestController
@RequestMapping("cities")
class CityController(private val cityService: CityService) {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{cityId}")
    fun findOne(@NotNull @ValidUUID @PathVariable("cityId") cityId: String): CityEntity {
        return cityService.findOne(UUID.fromString(cityId))
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    fun findAll(
        @PageableDefault(size = 50, sort = ["name"], direction = Sort.Direction.ASC) pageable: Pageable
    ): Page<CityEntity> {
        return cityService.findAll(pageable)
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    fun create(@Valid @RequestBody cityCreateDto: CityCreateDto): CityEntity {
        return cityService.create(cityCreateDto)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{cityId}")
    fun delete(@NotNull @ValidUUID @PathVariable("cityId") cityId: String) {
        cityService.delete(UUID.fromString(cityId))
    }

}