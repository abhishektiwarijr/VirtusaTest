package com.jr.virtusatest.presentation.dog_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jr.virtusatest.common.Result
import com.jr.virtusatest.domain.usecase.GetDogDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DogDetailsViewModel @Inject constructor(
    private val getDogDetailsUseCase: GetDogDetailsUseCase,
) : ViewModel() {
    private var _dogDetailsState = MutableStateFlow(DogDetailsState())
    val dogDetailsState: StateFlow<DogDetailsState> = _dogDetailsState.asStateFlow()

    fun getDogDetailsByBreedName(dogBreedName: String) {
        getDogDetailsUseCase.getDogDetailsByBreedName(dogBreedName).onEach { result ->
            when (result) {
                is Result.Success -> {
                    _dogDetailsState.value = DogDetailsState(
                        dogImageUrl = result.data?.dogImageUrl
                    )
                }

                is Result.Error -> {
                    _dogDetailsState.value = DogDetailsState(
                        error = result.message ?: "An unexpected error"
                    )
                }

                is Result.Loading -> {
                    _dogDetailsState.value = DogDetailsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}