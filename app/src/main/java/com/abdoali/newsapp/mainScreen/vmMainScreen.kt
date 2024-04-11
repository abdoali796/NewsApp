package com.abdoali.newsapp.mainScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.abdoali.newsapp.date.domain.Article
import com.abdoali.newsapp.date.domain.NewsApi
import com.abdoali.newsapp.date.repo.RepositoryImp
import com.abdoali.newsapp.uiCompound.NavigationScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VmMainScreen @Inject constructor(private val repository: RepositoryImp) : ViewModel() {

//    private var _news = Flow<PagingData<Article?(null)
//    val news: StateFlow<NewsApi?>
//        get() = _news

    var query = MutableStateFlow("")
        private set

    //    init {
//        viewModelScope.launch {
//            _news.update { repository.getNews(q) }
//        }
//    }
//
    fun updateQuery(q: String) {
        query.value = q
    }

    @OptIn(FlowPreview::class)
    fun getNews(): Flow<PagingData<Article>>? {
        if (query.value.isBlank()) return null
Log.i("getNews" ,"called")
        return   repository.getNews(query.value).debounce(1999L).cachedIn(viewModelScope)
    }

     fun saveArticle(article: Article){
        viewModelScope.launch {
            repository.saveArticle(article)
        }
    }
    //    fun search() {
//        viewModelScope.launch {
//            _news.update { repository.getNews(query.value) }
//        }
//    }

}