package com.example.rupikz.dto

import com.example.rupikz.common.validation.ValidUUID
import org.springframework.format.annotation.DateTimeFormat
import java.sql.Date
import java.util.UUID
import javax.validation.constraints.*

data class TemperatureCreateDto(
    @get:NotBlank
    @get:DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    val date: Date,

    @get:NotBlank
    val celsius: Int,

    @get:NotBlank
    @ValidUUID
    val cityId: UUID
)
