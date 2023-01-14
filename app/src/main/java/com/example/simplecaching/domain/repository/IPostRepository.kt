package com.example.simplecaching.domain.repository

import com.example.simplecaching.domain.models.Post
import com.example.simplecaching.others.Resource

interface IPostRepository {

    suspend fun getAllPosts(): Resource<List<Post>>

    suspend fun getPost(id: Int): Resource<Post>

}