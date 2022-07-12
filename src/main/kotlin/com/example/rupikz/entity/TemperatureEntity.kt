package com.example.rupikz.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import java.util.Date
import javax.persistence.*
import javax.validation.constraints.Min
import javax.validation.constraints.Max

@Entity
@Table(name = "temperatures")
data class TemperatureEntity(

    @Temporal(TemporalType.DATE)
    @Column(nullable = false, unique = true)
    var date: Date? = null,

    @Max(100)
    @Min(0)
    @Column(nullable = false)
    var celsius: Int? = null,

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "city_id", nullable = false)
    var city: CityEntity? = null

) : BaseEntity()
