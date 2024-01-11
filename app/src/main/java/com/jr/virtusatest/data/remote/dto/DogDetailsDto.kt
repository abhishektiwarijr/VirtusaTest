package com.jr.virtusatest.data.remote.dto

import android.os.Parcelable
import com.jr.virtusatest.domain.model.DogDetails
import kotlinx.parcelize.Parcelize

@Parcelize
data class DogDetailsDto(
    val message: String,
    val status: String
) : Parcelable

fun DogDetailsDto.toDogDetails(): DogDetails {
    return DogDetails(message)
}