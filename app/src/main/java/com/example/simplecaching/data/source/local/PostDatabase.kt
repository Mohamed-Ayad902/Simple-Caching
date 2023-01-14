package com.example.simplecaching.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database([PostEntity::class], version = 1)
abstract class PostDatabase : RoomDatabase() {

    abstract fun dao(): PostDao

}