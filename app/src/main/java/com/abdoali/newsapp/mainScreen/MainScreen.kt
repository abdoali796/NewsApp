package com.abdoali.newsapp.mainScreen

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.abdoali.newsapp.uiCompound.ArticleCart
import com.abdoali.newsapp.uiCompound.ErrorCard
import com.abdoali.newsapp.uiCompound.Loading
import com.abdoali.newsapp.uiCompound.LoadingItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen() {
    val vm: VmMainScreen = hiltViewModel()
    val query by vm.query.collectAsState()
    val news = vm.getNews()?.collectAsLazyPagingItems()
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current


    Column {
        OutlinedTextField(value = query,
            onValueChange = vm::updateQuery,
            trailingIcon = {
                Icon(Icons.Outlined.Search,
                    contentDescription = "search",
                    modifier = Modifier.clickable { vm.getNews() })
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onSearch = {
                keyboardController?.hide()
            }),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 8.dp, end = 8.dp, bottom = 0.dp)

        )

        LazyColumn(
            Modifier
                .fillMaxSize()
                .animateContentSize()
        ) {

            if (news != null) {
                items(news.itemCount, key = { it }) { index ->
                    news[index]?.let {
                        ArticleCart(
                            article = it,
                            isLocal = false,
                        ) {
                            vm.saveArticle(it)
                            Toast.makeText(context, "Article Saved ", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                item {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .animateItemPlacement()
                    ) {
                        Text(text = "no data :)")
                    }
                }
            }

//            news?.let {
//                items(it) { art ->
//                    ArticleCart(article = art)
//                }
//            }

            when (news?.loadState?.append) {
                is LoadState.Error -> {
                    item {
                        ErrorCard(error = (news.loadState.append as LoadState.Error).error.message.toString())

                    }
                }

                LoadState.Loading -> {
                    item {
                        LoadingItem()
                    }
                }

                is LoadState.NotLoading -> Unit
                else -> {

                }
            }
            when (news?.loadState?.refresh) {
                is LoadState.Error -> {
                    item {
                        ErrorCard(
                            error = (news.loadState.refresh as LoadState.Error).error.message.toString()
                        )
                    }
                }

                LoadState.Loading -> item {
                    Loading()
                }

                is LoadState.NotLoading -> {
                    Unit
                }

                else -> {}
            }
        }
//        news.apply {
//            when (news.loadState.refresh) {
//                is LoadState.Error -> {
//                    Log.i("abdoali", "ereeeeeeeeee")
//                }
//                 is LoadState.Loading -> { // Loading UI
//
//                    Column(
//                        modifier = Modifier
//                            .fillParentMaxSize(),
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Center,
//                    ) {
//                        Text(
//                            modifier = Modifier
//                                .padding(8.dp),
//                            text = "Refresh Loading"
//                        )
//
//                        CircularProgressIndicator(color = Color.Black)
//                    }
//
//            }
//
//                else -> {}
//            }
//        }
//    }

    }
}

