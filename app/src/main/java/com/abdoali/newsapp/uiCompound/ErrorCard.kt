package com.abdoali.newsapp.uiCompound

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abdoali.newsapp.R

@Composable
fun ErrorCard(
    error: String
) {
    Card(
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.error),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)

    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = stringResource(R.string.error)
            )
            Text(text = error)
        }
    }

}

@Preview
@Composable
private fun ErrorCardPre() {
    ErrorCard("kodddddddggggggg")
}