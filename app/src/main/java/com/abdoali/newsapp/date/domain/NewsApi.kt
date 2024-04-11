package com.abdoali.newsapp.date.domain

data class NewsApi(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)