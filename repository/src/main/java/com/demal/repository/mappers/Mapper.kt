package com.demal.repository.mappers

interface Mapper<I, O> {
    fun map(input: I): O
}