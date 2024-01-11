package com.jr.virtusatest.domain.usecase

import com.jr.virtusatest.common.Result
import com.jr.virtusatest.data.remote.dto.DogDetailsDto
import com.jr.virtusatest.domain.model.DogBreed
import com.jr.virtusatest.domain.model.DogDetails
import com.jr.virtusatest.domain.repository.DogRepository
import com.jr.virtusatest.mockdata.fetchDogBreedsMockData
import com.jr.virtusatest.mockdata.fetchDogDetailsMockData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class GetDogDetailsUseCaseImplTest {
    private val testDispatcher = StandardTestDispatcher()
    private var dogRepository: DogRepository = mockk()
    private lateinit var getDogDetailsUseCase: GetDogDetailsUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        Dispatchers.setMain(testDispatcher)

        getDogDetailsUseCase = GetDogDetailsUseCaseImpl(dogRepository, testDispatcher)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `getDogDetailsByBreedName success`() = runTest {
        // Arrange
        val fakeDogDetails = fetchDogDetailsMockData()
        coEvery { dogRepository.getDogDetailsByBreedName("affenpinscher") } returns fakeDogDetails

        // Act
        val result: MutableList<Result<DogDetails>> = mutableListOf()
        val flow: Flow<Result<DogDetails>> = getDogDetailsUseCase.getDogDetailsByBreedName("affenpinscher")

        // Assert
        flow.collect {
            result.add(it)
        }

        assertEquals(2, result.size) // Loading + Success
        assert(result[0] is Result.Loading)
        assert(result[1] is Result.Success)
        assert(result[1].data?.dogImageUrl?.isNotBlank() == true)
    }

    @Test
    fun `getDogDetailsByBreedName error - HTTP exception`() = runTest {
        // Arrange
        coEvery { dogRepository.getDogDetailsByBreedName("Bulldog") } throws (HttpException(Response.success(null)))

        // Act
        val result: MutableList<Result<DogDetails>> = mutableListOf()
        val flow: Flow<Result<DogDetails>> = getDogDetailsUseCase.getDogDetailsByBreedName("Bulldog")

        // Assert
        flow.collect {
            result.add(it)
        }

        assertEquals(2, result.size) // Loading + Error
        assert(result[0] is Result.Loading)
        assert(result[1] is Result.Error)
    }

    @Test
    fun `getDogDetailsByBreedName error - IOException`() = runTest {
        // Arrange
        coEvery {dogRepository.getDogDetailsByBreedName("Bulldog") } throws IOException()

        // Act
        val result: MutableList<Result<DogDetails>> = mutableListOf()
        val flow: Flow<Result<DogDetails>> = getDogDetailsUseCase.getDogDetailsByBreedName("Bulldog")

        // Assert
        flow.collect {
            result.add(it)
        }

        assertEquals(2, result.size) // Loading + Error
        assert(result[0] is Result.Loading)
        assert(result[1] is Result.Error)
    }
}