package com.murerwa.rickandmortytesting.fixtures

class JsonFileGetter {
    fun getJson(path: String): String {
        return this::class.java.classLoader?.getResource(path)?.readText() ?: ""
    }
}