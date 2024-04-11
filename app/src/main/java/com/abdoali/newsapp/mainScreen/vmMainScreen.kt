package com.abdoali.newsapp.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.abdoali.newsapp.date.domain.Article
import com.abdoali.newsapp.date.repo.RepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VmMainScreen @Inject constructor(private val repository: RepositoryImp) : ViewModel() {


    var query = MutableStateFlow("")
        private set


    fun updateQuery(q: String) {
        query.value = q
    }

    @OptIn(FlowPreview::class)
    fun getNews(): Flow<PagingData<Article>>? {
        if (query.value.isBlank()) return null
      //  debounce to Preventing too many requests
        return repository.getNews(query.value).debounce(1000L).cachedIn(viewModelScope)
    }

    fun saveArticle(article: Article) {
        viewModelScope.launch {
            repository.saveArticle(article)
        }
    }

}