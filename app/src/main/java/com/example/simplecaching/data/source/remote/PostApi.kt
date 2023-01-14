package com.example.simplecaching.data.source.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostApi {

    @GET("posts")
    suspend fun getAllPosts(): Response<List<PostDto>>

    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") id: Int): Response<PostDto>

}