package com.jin.ch03project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.jin.ch03project.ui.theme.Ch03ProjectTheme

class CardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ch03ProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier.padding(innerPadding), color = MaterialTheme.colorScheme.background
                    ) {
                        Column {
                            CardEx(cardData)
                            CardEx(cardData)
                        }
                    }
                }
            }
        }
    }

    companion object {
        val cardData = CardData(
            imageUrl = "https://raw.githubusercontent.com/Fastcampus-Android-Lecture-Project-2023/part4-chapter3/main/part4-chapter3-10/app/src/main/res/drawable-xhdpi/wall.jpg",
            imageDescription = "엔텔로프 캐년",
            author = "Dalinaum",
            description = "엔텔로프 캐년은 죽기 전에 꼭 봐야할 절경으로 소개되었습니다."
        )
    }
}

@Composable
fun CardEx(cardData: CardData) {
    val placeHolderColor = Color(0xff333333)
    Card(elevation = CardDefaults.cardElevation(8.dp), modifier = Modifier.padding(4.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = cardData.imageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = cardData.imageDescription,
                placeholder = ColorPainter(placeHolderColor),
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Column {
                Text(cardData.author)
                Spacer(modifier = Modifier.size(4.dp))
                Text(cardData.description)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview8() {
    Ch03ProjectTheme {
        Row {
            CardEx(CardActivity.cardData)
        }
    }
}


data class CardData(val imageUrl: String, val imageDescription: String, val author: String, val description: String)