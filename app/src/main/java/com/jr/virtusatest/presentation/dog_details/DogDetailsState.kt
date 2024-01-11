package com.jr.virtusatest.presentation.dog_details

data class DogDetailsState(
    val isLoading: Boolean = false,
    val dogImageUrl: String? = null,
    val error: String? = null
)
