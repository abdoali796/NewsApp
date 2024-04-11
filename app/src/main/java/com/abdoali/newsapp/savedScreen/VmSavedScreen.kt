package com.abdoali.newsapp.savedScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdoali.newsapp.date.domain.Article
import com.abdoali.newsapp.date.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VmSavedScreen @Inject constructor(private val repository: Repository) : ViewModel() {


      val list:Flow<List<Article>> = repository.getArticle()
      fun deleteArticle(article: Article){
            viewModelScope.launch {
                  repository.deleteArticle(article.title)
            }
      }
}