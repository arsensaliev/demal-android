package com.demal.model.data.entity.list

@Target(AnnotationTarget.FIELD)
annotation class Content

@Target(AnnotationTarget.FIELD)
annotation class ListItemId

fun <T> T.compareAnnotatedFields(
    other: T,
    annotationClass: Class<out Annotation>
): Boolean {
    val inClass = other!!::class.java

    if (this!!::class.java != inClass) return false

    val contentFields = inClass.declaredFields.filter { field ->
        field.isAnnotationPresent(annotationClass)
    }

    if (contentFields.isNullOrEmpty()) return equals(other)

    contentFields.forEach { field ->
        field.isAccessible = true
        val thisField = field.get(this)
        val otherField = field.get(other)

        if (thisField != otherField) return false
    }

    return true
}