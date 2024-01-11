package com.jr.virtusatest.domain.usecase

import com.jr.virtusatest.common.Result
import com.jr.virtusatest.data.remote.dto.DogDetailsDto
import com.jr.virtusatest.data.remote.dto.toDogBreed
import com.jr.virtusatest.data.remote.dto.toDogDetails
import com.jr.virtusatest.di.IoDispatcher
import com.jr.virtusatest.domain.model.DogBreed
import com.jr.virtusatest.domain.model.DogDetails
import com.jr.virtusatest.domain.repository.DogRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetDogDetailsUseCaseImpl @Inject constructor(
    private val dogBreedRepository: DogRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : GetDogDetailsUseCase {

    override fun getDogDetailsByBreedName(dogBreedName: String): Flow<Result<DogDetails>> = flow {
        try {
            emit(Result.Loading<DogDetails>())
            val dogDetails = dogBreedRepository.getDogDetailsByBreedName(dogBreedName)
            emit(Result.Success<DogDetails>(dogDetails.toDogDetails()))
        } catch (e: HttpException) {
            emit(
                Result.Error<DogDetails>(
                    message = e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        } catch (e: IOException) {
            emit(
                Result.Error<DogDetails>(
                    message = "Couldn't reach server. Check your internet connection."
                )
            )
        }
    }.flowOn(ioDispatcher)
}