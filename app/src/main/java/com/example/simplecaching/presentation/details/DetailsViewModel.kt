package com.example.simplecaching.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplecaching.domain.use_cases.details.GetPostUseCase
import com.example.simplecaching.others.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getPostUseCase: GetPostUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(DetailsUiState())
    val state = _state.asStateFlow()

    fun getPost(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getPostUseCase(id).collectLatest { response ->
                when (response) {
                    is Resource.Error -> _state.update {
                        it.copy(
                            isLoading = false,
                            error = response.message!!,
                            post = response.data!!
                        )
                    }
                    is Resource.Loading -> _state.update {
                        it.copy(
                            isLoading = true,
                            error = null,
                            post = null
                        )
                    }
                    is Resource.Success -> _state.update {
                        it.copy(
                            isLoading = false,
                            error = null,
                            post = response.data!!
                        )
                    }
                }
            }
        }
    }

}