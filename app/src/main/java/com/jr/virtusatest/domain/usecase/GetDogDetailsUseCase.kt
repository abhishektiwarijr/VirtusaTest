package com.jr.virtusatest.domain.usecase

import com.jr.virtusatest.common.Result
import com.jr.virtusatest.domain.model.DogDetails
import kotlinx.coroutines.flow.Flow

interface GetDogDetailsUseCase {
    fun getDogDetailsByBreedName(dogBreedName : String): Flow<Result<DogDetails>>
}