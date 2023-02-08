package com.murerwa.rickandmortytesting.features.characters.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Origin(
    val name: String,
    val url: String
): Parcelable