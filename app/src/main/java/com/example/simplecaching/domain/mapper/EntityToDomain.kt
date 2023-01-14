package com.example.simplecaching.domain.mapper

import com.example.simplecaching.data.source.local.PostEntity
import com.example.simplecaching.domain.models.Post

class EntityToDomain : Mapper<PostEntity, Post> {

    override fun map(input: PostEntity): Post {
        return Post(input.body, input.title,input.id)
    }
}