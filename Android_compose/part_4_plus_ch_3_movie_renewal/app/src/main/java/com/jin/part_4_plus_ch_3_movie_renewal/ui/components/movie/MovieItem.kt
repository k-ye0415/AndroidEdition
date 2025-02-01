package com.jin.part_4_plus_ch_3_movie_renewal.ui.components.movie

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jin.part_4_plus_ch_3_movie_renewal.R
import com.jin.part_4_plus_ch_3_movie_renewal.ui.theme.Paddings

private val CARD_WIDTH = 150.dp
private val ICON_SIZE = 12.dp

@Composable
fun MovieItem() {
    Column(
        modifier = Modifier
            .width(CARD_WIDTH)
            .padding(Paddings.large)
    ) {
        Poster(
            modifier = Modifier.width(CARD_WIDTH)
        )
        Text(
            text = "The load the Ring 1",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = Paddings.large),
            color = Color.White,
            style = MaterialTheme.typography.bodySmall
        )
        Row(
            modifier = Modifier.padding(vertical = Paddings.medium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .padding(Paddings.small)
                    .size(ICON_SIZE),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_rating),
                tint = Color.White.copy(alpha = 0.5f),
                contentDescription = "rating icon"
            )
            Text(text = "5.0", color = Color.White.copy(alpha = 0.5f ), style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun Poster(modifier: Modifier) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Box(modifier = Modifier.background(Color.Blue))
    }
}

@Preview
@Composable
fun MovieItemPreview() {
    MovieItem()
}