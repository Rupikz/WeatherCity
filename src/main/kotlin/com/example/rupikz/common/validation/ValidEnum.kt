package com.example.rupikz.common.validation

import java.util.Locale
import javax.validation.*
import kotlin.collections.ArrayList
import kotlin.reflect.KClass
import kotlin.annotation.AnnotationTarget.*

@Constraint(validatedBy = [EnumValidatorImpl::class])
@Target(FIELD, PROPERTY_GETTER)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@ReportAsSingleViolation
annotation class IsEnum(
    val enumClass: KClass<out Enum<*>>,
    val message: String = "Value is not valid",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class EnumValidatorImpl : ConstraintValidator<IsEnum, String?> {
    private val valueList: MutableList<String> = ArrayList()

    override fun isValid(value: String?, context: ConstraintValidatorContext): Boolean {
        if (value == null) {
            return true
        }
        return valueList.contains(value.toUpperCase(Locale.getDefault()))
    }

    override fun initialize(constraintAnnotation: IsEnum) {
        val enumClass: KClass<out Enum<*>> = constraintAnnotation.enumClass
        val enumValArr = enumClass.java.enumConstants
        for (enumVal in enumValArr) {
            valueList.add(enumVal.toString().toUpperCase(Locale.getDefault()))
        }
    }
}