package com.example.rupikz.entity

import javax.persistence.*
import com.example.rupikz.enum.CityType
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.hibernate.Hibernate

// TODO: Data class?
// TODO: Как использовать lateinit
// TODO: Как настроить автоформатирование
// TODO: Добавить ограничения: uniq, length

@Entity
@Table(name = "cities")
data class CityEntity(

    @Column(nullable = false)
    var name: String? = null,

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    var type: CityType? = null,

    @Column(length = 255)
    var description: String? = null,

    @JsonManagedReference
    @OneToMany(mappedBy = "city", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val temperatures: List<TemperatureEntity>? = null

) : BaseEntity() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as CityEntity

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}
