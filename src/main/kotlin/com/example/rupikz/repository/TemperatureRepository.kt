package com.example.rupikz.repository

import com.example.rupikz.entity.TemperatureEntity
import java.util.Date
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository

interface TemperatureRepository : JpaRepository<TemperatureEntity, UUID> {
    fun existsByDate(date: Date): Boolean
}
