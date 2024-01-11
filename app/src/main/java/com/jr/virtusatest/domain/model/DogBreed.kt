package com.jr.virtusatest.domain.model

data class DogBreed(
    val dogs: List<DogName>
)

data class DogName(
    val dogFullName: String,
    val dogBreedName: String,
    val dogSubBreedName: String? = null,
)
