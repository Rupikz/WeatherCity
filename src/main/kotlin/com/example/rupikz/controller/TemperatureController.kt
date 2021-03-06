package com.example.rupikz.controller

import com.example.rupikz.common.validation.ValidUUID
import com.example.rupikz.dto.TemperatureCreateDto
import com.example.rupikz.dto.request.TemperatureAverageDto
import com.example.rupikz.entity.TemperatureEntity
import com.example.rupikz.service.TemperatureService
import io.swagger.v3.oas.annotations.Operation
import org.springdoc.api.annotations.ParameterObject
import org.springframework.web.bind.annotation.*
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import javax.validation.Valid
import javax.validation.constraints.NotNull
import java.util.UUID
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault

@Validated
@RestController
@RequestMapping("temperatures")
class TemperatureController(private val temperatureService: TemperatureService) {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    fun findAll(
        @ParameterObject @PageableDefault(size = 50, sort = ["date"], direction = Sort.Direction.ASC) pageable: Pageable
    ): Page<TemperatureEntity> {
        return temperatureService.findAll(pageable)
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}/average")
    fun average(
        @NotNull @ValidUUID @PathVariable("id") id: String,
        @Valid @RequestBody temperatureAverageDto: TemperatureAverageDto // TODO: почему нельзя валидировать класс в RequestParam?
    ): Double {
        return temperatureService.average(UUID.fromString(id), temperatureAverageDto)
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    fun create(@Valid @RequestBody temperatureCreateDto: TemperatureCreateDto): TemperatureEntity {
        return temperatureService.create(temperatureCreateDto)
    }

    @Operation(summary = "Delete a temperature by its id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    fun delete(@NotNull @ValidUUID @PathVariable("id") id: String) {
        temperatureService.delete(UUID.fromString(id))
    }

}