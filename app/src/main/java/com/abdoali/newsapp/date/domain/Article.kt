package com.abdoali.newsapp.date.domain

import com.abdoali.newsapp.date.local.ArticleEntity

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)

fun Article.toArticleEntity(): ArticleEntity {
    return ArticleEntity(
        url = this.url,
        author = this.author,
        content = this.content,
        description = this.description,
        publishedAt = this.publishedAt,
        source = this.source.name,
        title = this.title,
        urlToImage = this.urlToImage

    )
}