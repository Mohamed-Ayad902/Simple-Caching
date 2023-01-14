package com.example.simplecaching.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplecaching.domain.use_cases.home.GetPostsUseCase
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
class HomeViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getPosts()
        }
    }

    private suspend fun getPosts() {
        getPostsUseCase().collectLatest { response ->
            when (response) {
                is Resource.Error -> _state.update {
                    it.copy(
                        isLoading = false,
                        error = response.message!!,
                        posts = response.data ?: emptyList()
                    )
                }
                is Resource.Loading -> _state.update { it.copy(isLoading = true, error = null) }
                is Resource.Success -> _state.update {
                    it.copy(
                        isLoading = false,
                        error = null,
                        posts = response.data!!
                    )
                }
            }
        }
    }

}