package com.murerwa.rickandmortytesting.core.models

data class ItemsResponse<T>(
    val info: Info,
    val results: List<T>
)