package com.abdoali.newsapp.savedScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.abdoali.newsapp.R
import com.abdoali.newsapp.uiCompound.ArticleCart

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SavedScreen() {
    val vmSavedScreen: VmSavedScreen = hiltViewModel()

    val saved = vmSavedScreen.list.collectAsState(null)

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = stringResource(R.string.saved_article)) })
    }) { padding ->

        LazyColumn(
            Modifier.padding(padding)
        ) {

            if (saved.value != null) {
                items(items = saved.value!!, key = { it.title }) {
                    ArticleCart(
                        article = it, isLocal = true, modifier = Modifier.animateItemPlacement()
                    ) {
                        vmSavedScreen.deleteArticle(it)
                    }
                }
            }
        }
    }
}