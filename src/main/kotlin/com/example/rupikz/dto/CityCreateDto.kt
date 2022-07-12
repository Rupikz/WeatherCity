package com.example.rupikz.dto

import com.example.rupikz.enum.CityType
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.validation.constraints.Size
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CityCreateDto(
    @get:NotBlank
    @get:Size(min=5, max=15)
    val name: String,

    @get:NotNull
    @get:Enumerated(EnumType.STRING)
    val type: CityType,

    @get:Size(max=255)
    val description: String?
)
