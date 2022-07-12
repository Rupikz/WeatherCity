package com.example.rupikz.dto.request

import org.springframework.format.annotation.DateTimeFormat
import java.util.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Past

data class TemperatureAverageDto(
    @get:NotNull
    @get:DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @get:Past()
    val periodStart: Date,

    @get:NotNull
    @get:DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @get:Past()
    val periodEnd: Date
)
