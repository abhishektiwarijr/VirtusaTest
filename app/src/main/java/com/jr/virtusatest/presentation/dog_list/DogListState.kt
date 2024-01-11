package com.jr.virtusatest.presentation.dog_list

import com.jr.virtusatest.domain.model.DogName

data class DogListState(
    val isLoading: Boolean = false,
    val dogBreeds: List<DogName> = emptyList(),
    val error: String? = null
)
