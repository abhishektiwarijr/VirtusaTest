package com.jr.virtusatest.data.remote

import com.jr.virtusatest.data.remote.dto.DogBreedDto
import com.jr.virtusatest.data.remote.dto.DogDetailsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApi {

    @GET("breeds/list/all")
    suspend fun getAllBreeds(): DogBreedDto

    @GET("breed/{breed_name}/images/random")
    suspend fun getDogDetailsByBreedName(@Path("breed_name") dogBreedName : String): DogDetailsDto
}