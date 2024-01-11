package com.jr.virtusatest.data.repository

import com.jr.virtusatest.data.remote.DogApi
import com.jr.virtusatest.data.remote.dto.DogBreedDto
import com.jr.virtusatest.data.remote.dto.DogDetailsDto
import com.jr.virtusatest.domain.repository.DogRepository
import javax.inject.Inject

class DogRepositoryImpl @Inject constructor(
    private val dogApi: DogApi
) : DogRepository {

    override suspend fun getDogBreeds(): DogBreedDto {
        return dogApi.getAllBreeds()
    }

    override suspend fun getDogDetailsByBreedName(dogBreedName: String): DogDetailsDto {
        return dogApi.getDogDetailsByBreedName(dogBreedName)
    }
}