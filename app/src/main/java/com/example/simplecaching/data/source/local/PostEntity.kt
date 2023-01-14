package com.example.simplecaching.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class PostEntity(
    val body: String,
    @PrimaryKey val id: Int,
    val title: String
)