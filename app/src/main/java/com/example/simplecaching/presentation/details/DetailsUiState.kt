package com.example.simplecaching.presentation.details

import com.example.simplecaching.domain.models.Post

data class DetailsUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val post: Post? = null
)
