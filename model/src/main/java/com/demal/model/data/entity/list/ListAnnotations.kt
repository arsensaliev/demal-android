package com.demal.model.data.entity.list


/**
 * Аннотация, которой должны быть помечены все отображаемые
 * поля элемента списка.
 * */
@Target(AnnotationTarget.FIELD)
annotation class Content


/**
 * Аннотация, которой должны быть помечены все поля, сравнение
 * которых необходимо для идентификации элементов списка.
 * Часто достаточнто пометить этой аннотацией только id.
 * */
@Target(AnnotationTarget.FIELD)
annotation class ListItemId

/**
 * Метод для сравнения двух объектов по полям,
 * аннотированым переданой в аргументы аннотацией
 * @param annotationClass - аннотация по которой
 * будет проводится сравнение
 * */
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