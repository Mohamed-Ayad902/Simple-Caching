package com.example.simplecaching.domain.mapper

import com.example.simplecaching.data.source.local.PostEntity
import com.example.simplecaching.data.source.remote.PostDto

class DtoToEntity : Mapper<PostDto, PostEntity> {

    override fun map(input: PostDto): PostEntity {
        return PostEntity(
            input.body,
            input.id,
            input.title
        )
    }
}