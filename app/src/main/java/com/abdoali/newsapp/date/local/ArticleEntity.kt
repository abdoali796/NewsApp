package com.abdoali.newsapp.date.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abdoali.newsapp.date.domain.Article
import com.abdoali.newsapp.date.domain.Source

@Entity(tableName = "article")
data class ArticleEntity(
    @PrimaryKey()
    val title: String,
    val url: String,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: String,
    val urlToImage: String
)

fun ArticleEntity.toArticle(): Article {
    return Article(
        url = this.url,
        author = this.author,
        content = this.content,
        description = this.description,
        publishedAt = this.publishedAt,
        source = Source(id = "" , name = this.source),
        title = this.title,
        urlToImage = this.urlToImage

    )
}