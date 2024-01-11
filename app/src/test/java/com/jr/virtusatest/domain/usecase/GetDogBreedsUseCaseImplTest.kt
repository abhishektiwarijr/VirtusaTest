package com.jr.virtusatest.domain.usecase

import com.jr.virtusatest.common.Result
import com.jr.virtusatest.data.remote.dto.toDogBreed
import com.jr.virtusatest.domain.model.DogBreed
import com.jr.virtusatest.domain.repository.DogRepository
import com.jr.virtusatest.mockdata.fetchDogBreedsMockData
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
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


class GetDogBreedsUseCaseImplTest {
    private val testDispatcher = StandardTestDispatcher()
    private var dogRepository: DogRepository = mockk()
    private lateinit var getDogBreedsUseCase: GetDogBreedsUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        Dispatchers.setMain(testDispatcher)

        getDogBreedsUseCase = GetDogBreedsUseCaseImpl(dogRepository, testDispatcher)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `getDogBreeds success`() = runTest {
        // Arrange
        val fakeDogBreeds = fetchDogBreedsMockData()

        coEvery { dogRepository.getDogBreeds() } returns fakeDogBreeds

        // Act
        val result: MutableList<Result<DogBreed>> = mutableListOf()
        val flow: Flow<Result<DogBreed>> = getDogBreedsUseCase.getDogBreeds()

        // Assert
        flow.collect {
            result.add(it)
        }

        assertEquals(2, result.size) // Loading + Success
        assert(result[0] is Result.Loading)
        assert(result[1] is Result.Success)
        assert(result[1].data?.dogs?.isEmpty()?.not() == true)
    }

    @Test
    fun `getDogBreeds error - HTTP exception`() = runTest {
        // Arrange
        coEvery { dogRepository.getDogBreeds() } throws (HttpException(Response.success(null)))

        // Act
        val result: MutableList<Result<DogBreed>> = mutableListOf()
        val flow: Flow<Result<DogBreed>> = getDogBreedsUseCase.getDogBreeds()

        // Assert
        flow.collect {
            result.add(it)
        }

        assertEquals(2, result.size) // Loading + Error
        assert(result[0] is Result.Loading)
        assert(result[1] is Result.Error)
    }

    @Test
    fun `getDogBreeds error - IOException`() = runTest {
        // Arrange
        coEvery { dogRepository.getDogBreeds() } throws IOException()

        // Act
        val result: MutableList<Result<DogBreed>> = mutableListOf()
        val flow: Flow<Result<DogBreed>> = getDogBreedsUseCase.getDogBreeds()

        // Assert
        flow.collect {
            result.add(it)
        }

        assertEquals(2, result.size) // Loading + Error
        assert(result[0] is Result.Loading)
        assert(result[1] is Result.Error)
    }
}