package com.example.rupikz.dto

import com.example.rupikz.common.validation.ValidUUID
import org.springframework.format.annotation.DateTimeFormat
import java.util.*
import javax.validation.constraints.*

data class TemperatureCreateDto(
    @get:NotNull
    @get:DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @get:Past()
    val date: Date,

    @get:NotNull
    val celsius: Int,

    @get:NotNull
    @ValidUUID
    val cityId: UUID
)
