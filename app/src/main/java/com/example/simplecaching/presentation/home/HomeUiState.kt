package com.example.simplecaching.presentation.home

import com.example.simplecaching.domain.models.Post

data class HomeUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val posts: List<Post> = emptyList()
)