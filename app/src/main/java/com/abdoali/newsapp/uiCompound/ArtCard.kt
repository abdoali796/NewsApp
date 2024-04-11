package com.abdoali.newsapp.uiCompound

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.abdoali.newsapp.R
import com.abdoali.newsapp.date.domain.Article
import com.abdoali.newsapp.date.domain.Source

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleCart(
    article: Article, isLocal: Boolean, modifier: Modifier = Modifier, action: () -> Unit
) {
    val width = LocalConfiguration.current.screenWidthDp / 6 * 7
    val high = LocalConfiguration.current.screenHeightDp / 5
    val uriHandler = LocalUriHandler.current

    Card(
        modifier = modifier.padding(8.dp)
    ) {
        Column(modifier.padding(8.dp)) {

            Box(
//                modifier = modifier.size(height = high.dp, width = width.dp)

            ) {


                AsyncImage(
                    model = article.urlToImage,
                    contentDescription = article.description,
                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
                    contentScale = ContentScale.FillBounds,
                    modifier = modifier.size(height = high.dp, width = width.dp)

                )
                Card(onClick = action) {
                    if (isLocal) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Delete")

                    } else {
                        Icon(imageVector = Icons.Default.AddCircle, contentDescription = "Save")

                    }
                }
            }
            Text(text = article.title, style = MaterialTheme.typography.titleLarge)
            Text(text = article.source.name, style = MaterialTheme.typography.titleSmall)
            Text(text = article.author, style = MaterialTheme.typography.labelSmall)

            Text(text = article.description, style = MaterialTheme.typography.bodySmall)
            Text(text = article.url,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Blue,
                maxLines = 2,
                modifier = Modifier.clickable {
                    uriHandler.openUri(article.url)
                })

            Text(text = article.publishedAt, style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Preview
@Composable
fun art() {
    val Article = Article(
        author = "abdo",
        content = "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk",
        description = "dddddddddddddddn;vdibgubnfunsnunucunucf",
        publishedAt = "20000003",
        source = Source(id = "33", "abdoali976"),
        title = "abdo anjfj jfnvjdcd",
        url = "fkfkf.sfio.sfkm",
        urlToImage = "http//www.xxx.com"

    )
    ArticleCart(article = Article, false, action = {})

}