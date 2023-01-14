package com.example.simplecaching.domain.use_cases.home

import com.example.simplecaching.domain.models.Post
import com.example.simplecaching.domain.repository.IPostRepository
import com.example.simplecaching.others.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val repository: IPostRepository
) {

    suspend operator fun invoke(): Flow<Resource<List<Post>>> = flow {
        emit(Resource.Loading())
        emit(repository.getAllPosts())
    }

}