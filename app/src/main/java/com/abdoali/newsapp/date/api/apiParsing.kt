package com.abdoali.newsapp.date.api

import com.abdoali.newsapp.date.domain.Article
import com.abdoali.newsapp.date.domain.NewsApi
import com.abdoali.newsapp.date.domain.Source
import org.json.JSONObject

fun apiParsing(jsonObject: JSONObject): NewsApi {
    val status = jsonObject.getString("status")
    val totalResults = jsonObject.getInt("totalResults")

    val articlesArray = jsonObject.getJSONArray("articles")
    val articlesList = mutableListOf<Article>()
    for (i in 0 until articlesArray.length()) {
        val articleJson = articlesArray.getJSONObject(i)
        val sourceJson = articleJson.getJSONObject("source")
        val id = sourceJson.getString("id")
        val name = sourceJson.getString("name")
        val source = Source(id = id, name = name)
        val author = articleJson.getString("author")
        val title = articleJson.getString("title")
        val description = articleJson.getString("description")
        val url = articleJson.getString("url")
        val urlToImage = articleJson.getString("urlToImage")
        val publishedAt = articleJson.getString("publishedAt")
        val content = articleJson.getString("content")
        val article = Article(
            author = author,
            content = content,
            description = description,
            publishedAt = publishedAt,
            source = source,
            title = title,
            url = url,
            urlToImage = urlToImage
        )
        articlesList += article
    }
    return NewsApi(articles = articlesList, status = status, totalResults = totalResults)
}