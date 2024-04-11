package com.abdoali.newsapp.date.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveArt(articleEntity: ArticleEntity)

    @Query("SELECT * FROM article")
    fun getArticle(): Flow<List<ArticleEntity>>

    @Query("DELETE  FROM article WHERE title =:title")
    suspend fun deleteArticle(title: String)

}