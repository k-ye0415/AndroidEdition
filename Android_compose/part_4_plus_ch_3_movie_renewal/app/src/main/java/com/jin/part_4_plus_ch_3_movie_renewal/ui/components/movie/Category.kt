package com.jin.part_4_plus_ch_3_movie_renewal.ui.components.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CategoryRow() {
    Column() {
        CategoryTitle("Action")
        LazyRow(
            contentPadding = PaddingValues(
                horizontal = 10.dp
            )
        ) /*RecyclerView 와 같은것*/{
//            itemsIndexed()
            item {
                MovieItem()
            }
        }
    }
}

@Composable
fun CategoryTitle(titleName: String) {
    Text(
        text = "Action",
        modifier = Modifier.padding(10.dp),
        color = Color.White
    )
}

@Preview
@Composable
fun CategoryRowPreview(){
    CategoryRow()
}