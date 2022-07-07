package com.example.rupikz.dto

import javax.validation.constraints.Size
import javax.validation.constraints.Pattern
import javax.validation.constraints.NotBlank

// TODO: почему @NotBlank - не работает, а @get:NotBlank - работает?
data class CityCreateDto(
    @get:NotBlank
    @get:Size(min=5, max=15)
    val name: String?,

    @get:NotBlank
    @get:Pattern(regexp = "^(SMALL|MEDIUM|LARGE)\$") // TODO: добавить валидайцию enum
    val type: String?,

    @get:Size(max=255)
    val description: String?
)
