package com.example.simplecaching.data.repository

import com.example.simplecaching.data.source.local.PostDao
import com.example.simplecaching.data.source.remote.PostApi
import com.example.simplecaching.domain.mapper.DtoToEntity
import com.example.simplecaching.domain.mapper.EntityToDomain
import com.example.simplecaching.domain.models.Post
import com.example.simplecaching.domain.repository.IPostRepository
import com.example.simplecaching.others.Resource
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postMapper: EntityToDomain,
    private val postEntityMapper: DtoToEntity,
    private val dao: PostDao,
    private val api: PostApi
) : IPostRepository {

    override suspend fun getAllPosts(): Resource<List<Post>> {
        val cachedPosts = dao.getAllPosts().map { postMapper.map(it) }
        try {
            val response = api.getAllPosts()
            response.body()?.let { postsDtos ->
                val entityPosts = postsDtos.map { postEntityMapper.map(it) }
                dao.addToPosts(entityPosts)
            }
        } catch (e: Exception) {
            return Resource.Error(e.message ?: e.toString(), data = cachedPosts)
        }
        val newPosts = dao.getAllPosts().map { postMapper.map(it) }
        return Resource.Success(newPosts)
    }

    override suspend fun getPost(id: Int): Resource<Post> {
        val cachedPost = postMapper.map(dao.getPost(id))
        try {
            val response = api.getPostById(id)
            response.body()?.let { postDto ->
                val entityPost = postEntityMapper.map(postDto)
                dao.addToPosts(entityPost)
            }
        } catch (e: Exception) {
            return Resource.Error(e.message ?: e.toString(), data = cachedPost)
        }
        val newPost = postMapper.map(dao.getPost(id))
        return Resource.Success(newPost)
    }
}