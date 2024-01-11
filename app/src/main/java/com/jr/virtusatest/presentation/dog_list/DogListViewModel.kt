package com.jr.virtusatest.presentation.dog_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jr.virtusatest.common.Result
import com.jr.virtusatest.domain.usecase.GetDogBreedsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DogListViewModel @Inject constructor(
    private val getDogBreedsUseCase: GetDogBreedsUseCase,
) : ViewModel() {
    private var _dogListState = MutableStateFlow(DogListState())
    val dogListState: StateFlow<DogListState> = _dogListState.asStateFlow()

    init {
        getDogBreeds()
    }

    fun getDogBreeds() {
        getDogBreedsUseCase.getDogBreeds().onEach { result ->
            when (result) {
                is Result.Success -> {
                    _dogListState.value = DogListState(
                        dogBreeds = result.data?.dogs ?: emptyList()
                    )
                }

                is Result.Error -> {
                    _dogListState.value = DogListState(
                        error = result.message ?: "An unexpected error"
                    )
                }

                is Result.Loading -> {
                    _dogListState.value = DogListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}