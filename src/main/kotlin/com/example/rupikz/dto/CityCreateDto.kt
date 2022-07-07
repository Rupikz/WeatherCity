package com.example.rupikz.dto

import com.example.rupikz.common.validation.IsEnum
import com.example.rupikz.enum.CityType
import javax.validation.constraints.Size
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

// TODO: почему @NotBlank - не работает, а @get:NotBlank - работает?

data class CityCreateDto(
    @get:NotBlank
    @get:Size(min=5, max=15)
    val name: String,

    @get:NotNull
    @get:IsEnum(CityType::class)
    val type: String, // TODO: как сразу указать здесь тип CityType?

    @get:Size(max=255)
    val description: String?
)
