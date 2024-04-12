package com.abdoali.newsapp.mainScreen

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.abdoali.newsapp.R
import com.abdoali.newsapp.uiCompound.ArticleCart
import com.abdoali.newsapp.uiCompound.ErrorCard
import com.abdoali.newsapp.uiCompound.Loading
import com.abdoali.newsapp.uiCompound.LoadingItem
import com.abdoali.newsapp.uiCompound.NoDate

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
            placeholder = { Text(text = stringResource(id = R.string.search))},
            trailingIcon = {
                Icon(Icons.Outlined.Search,
                    contentDescription = stringResource(R.string.search),
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
        if (news == null) {
            NoDate()
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {

            if (news != null) {
                items(news.itemCount, key = { it }) { index ->
                    news[index]?.let {
                        ArticleCart(
                            article = it,
                            isLocal = false,
                        ) {
                            vm.saveArticle(it)
                            Toast.makeText(
                                context,
                                context.getString(R.string.article_saved),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }


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
                    Unit
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

                else -> {
                    Unit
                }
            }
        }

    }
}

