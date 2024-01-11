package com.jr.virtusatest.domain.repository

import com.jr.virtusatest.data.remote.dto.DogBreedDto
import com.jr.virtusatest.data.remote.dto.DogDetailsDto

interface DogRepository {
    suspend fun getDogBreeds(): DogBreedDto
    suspend fun getDogDetailsByBreedName(dogBreedName: String): DogDetailsDto
}