package com.example.rupikz.common.validation

import java.lang.annotation.Documented
import java.lang.annotation.Repeatable
import javax.validation.Constraint
import javax.validation.Payload
import javax.validation.ReportAsSingleViolation
import javax.validation.constraints.Pattern
import kotlin.annotation.AnnotationRetention.*
import kotlin.annotation.AnnotationTarget.*
import kotlin.reflect.KClass

@Documented
@ReportAsSingleViolation
@Constraint(validatedBy = [])
@Pattern(
    regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$",
    flags = [Pattern.Flag.CASE_INSENSITIVE]
)
@Retention(RUNTIME)
@Target(FIELD, CONSTRUCTOR, PROPERTY, VALUE_PARAMETER, CLASS)
@Repeatable(
    ValidUUID.List::class
)
annotation class ValidUUID(
    val message: String = "Invalid UUID",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<Payload>> = []
) {
    @Target(FUNCTION, PROPERTY_GETTER, PROPERTY_SETTER, FIELD, ANNOTATION_CLASS, CONSTRUCTOR, VALUE_PARAMETER)
    @Retention(
        RUNTIME
    )
    @Documented
    annotation class List(vararg val value: ValidUUID)
}
