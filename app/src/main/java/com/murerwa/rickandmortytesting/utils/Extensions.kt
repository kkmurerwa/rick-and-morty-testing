package com.murerwa.rickandmortytesting.utils

import okhttp3.ResponseBody
import org.json.JSONObject
import java.util.*

fun ResponseBody?.readError(): String? {
    if (this == null) return null
    return try {
        var returnStringError = ""
        val jsonObj = JSONObject(this.charStream().readText())
        if (jsonObj.has("detail")) {
            returnStringError = jsonObj.getString("detail")
        } else {
            var errorCount = 0
            for (key in jsonObj.keys()) {
                val childArray = jsonObj.getJSONArray(key)
                for (i in 0 until childArray.length()) {
                    errorCount++
                    returnStringError =
                        returnStringError + "$errorCount. " + (childArray[i] as String).capitalizeString() + "\n"
                }
            }
        }
        returnStringError.trim()
    } catch (_: Exception) {
        null
    }
}

fun String.capitalizeString(): String {
    return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}