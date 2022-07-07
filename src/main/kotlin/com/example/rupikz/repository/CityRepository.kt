package com.example.rupikz.repository

import com.example.rupikz.entity.CityEntity
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository

interface CityRepository : JpaRepository<CityEntity, UUID> {
    fun existsByName(name: String): Boolean // TODO: как это работает?
}