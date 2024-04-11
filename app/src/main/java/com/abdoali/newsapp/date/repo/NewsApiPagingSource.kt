package com.abdoali.newsapp.date.repo

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.abdoali.newsapp.date.api.ApiService
import com.abdoali.newsapp.date.api.apiParsing
import com.abdoali.newsapp.date.domain.Article
import kotlinx.coroutines.delay
import org.json.JSONObject

class NewsApiPagingSource(
    val query: String, private val apiService: ApiService

) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {

            Log.i("abdoalieeeee", "page ${params.key} + ${params.loadSize}")
            val page = params.key ?: 1
            Log.i("abdoalieeeee", "call $query ")
            delay(1000)

            val responseJson = apiService.search(query = query, page = page)
            val response = apiParsing(JSONObject(responseJson))
            Log.i("abdoalieeeee", "respons ${response.totalResults}")
            LoadResult.Page(
                data = response.articles,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.articles.isEmpty()) null else page.plus(1),

                )
        } catch (e: Exception) {
            Log.i("abdoalieeeee", "cha ${e.localizedMessage}")

            LoadResult.Error(e)
        }

    }
}