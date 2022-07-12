package com.example.rupikz.repository

import com.example.rupikz.entity.TemperatureEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface TemperatureRepository : JpaRepository<TemperatureEntity, UUID> {
    fun existsByDate(date: Date): Boolean

    @Query("SELECT AVG(u.celsius) FROM TemperatureEntity u WHERE u.city.id = :cityId AND u.date BETWEEN :periodStart AND :periodEnd")
    fun averageByCityId(cityId: UUID, periodStart: Date, periodEnd: Date): Double
}
