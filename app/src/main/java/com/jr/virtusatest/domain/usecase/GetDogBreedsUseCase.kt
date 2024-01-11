package com.jr.virtusatest.domain.usecase

import com.jr.virtusatest.common.Result
import com.jr.virtusatest.domain.model.DogBreed
import kotlinx.coroutines.flow.Flow

interface GetDogBreedsUseCase {
    fun getDogBreeds(): Flow<Result<DogBreed>>
}