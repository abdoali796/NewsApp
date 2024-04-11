package com.abdoali.newsapp.date.local

import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper

@Database(entities = [ArticleEntity::class] , version = 1 , exportSchema = false)
abstract class ArticleDatabase :RoomDatabase() {
    abstract fun articleDao():ArticleDao
}