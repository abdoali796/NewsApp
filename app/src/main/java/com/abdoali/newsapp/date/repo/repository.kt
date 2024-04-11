package com.abdoali.newsapp.date.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.abdoali.newsapp.date.api.ApiService
import com.abdoali.newsapp.date.domain.Article
import com.abdoali.newsapp.date.domain.toArticleEntity
import com.abdoali.newsapp.date.local.ArticleDatabase
import com.abdoali.newsapp.date.local.toArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface Repository {
    fun getNews(q: String): Flow<PagingData<Article>>
    suspend fun saveArticle(article: Article)
    fun getArticle(): Flow<List<Article>>
    suspend fun deleteArticle(title: String)
}

class RepositoryImp @Inject constructor(
    private val apiService: ApiService,
    private val database: ArticleDatabase
) : Repository {


    override fun getNews(q: String) = Pager(

        config = PagingConfig(3, enablePlaceholders = false),
        pagingSourceFactory = { NewsApiPagingSource(query = q, apiService) }
    ).flow

    override suspend fun saveArticle(article: Article) {
        database.articleDao().saveArt(article.toArticleEntity())
    }

    override fun getArticle(): Flow<List<Article>> {
        return database.articleDao().getArticle().map { it.map { it.toArticle() } }
    }

    override suspend fun deleteArticle(title: String) {
        database.articleDao().deleteArticle(title)
    }
}
