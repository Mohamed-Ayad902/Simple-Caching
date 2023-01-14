package com.example.simplecaching.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToPosts(list: List<PostEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToPosts(list: PostEntity)

    @Query("select * from PostEntity")
    suspend fun getAllPosts(): List<PostEntity>

    @Query("select * from PostEntity where id like '%' || :id || '%'")
    suspend fun getPost(id: Int): PostEntity

}