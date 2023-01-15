package com.murerwa.rickandmortytesting.domain.models.common

data class ItemsResponse<T>(
    val info: Info,
    val results: List<T>
)