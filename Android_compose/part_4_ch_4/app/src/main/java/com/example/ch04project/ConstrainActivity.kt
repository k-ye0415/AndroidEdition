package com.example.ch04project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
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
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil3.compose.AsyncImage
import com.example.ch04project.ui.theme.Ch04ProjectTheme

class ConstrainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ch04ProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Greeting4(cardData = cardData)
                        Greeting4(cardData = cardData)
                        Greeting4(cardData = cardData)
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting4(modifier: Modifier = Modifier, cardData: CardData) {
    val placeHolderColor = Color(0x33000000)

    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier.padding(4.dp),
    ) {
        // 단계 1: 아래의 Row 레이아웃을 ConstraintLayout로 바꾸어 봅시다.
        // 내가 한거
//        ConstraintLayout(modifier = modifier.fillMaxWidth()) {
//            val (image, title, description) = createRefs()
//            AsyncImage(
//                model = cardData.imageUri,
//                contentDescription = cardData.imageDescription,
//                contentScale = ContentScale.Crop,
//                placeholder = ColorPainter(color = Color.Gray),
//                modifier = Modifier
//                    .clip(CircleShape)
//                    .size(40.dp)
//                    .constrainAs(image) {
//                        centerVerticallyTo(parent)
//                    }
//            )
//            Text(text = cardData.author, modifier = Modifier.constrainAs(title) {
//                start.linkTo(image.end, margin = 8.dp)
//            })
//            Text(text = cardData.description, modifier = Modifier
//                .background(Color.Green)
//                .constrainAs(description) {
//                    start.linkTo(image.end, margin = 8.dp)
//                    end.linkTo(parent.end, margin = 8.dp) // 이걸 선언해버리는 순간 UI 영역이 깨짐.
//                    bottom.linkTo(parent.bottom, margin = 8.dp)
//                    top.linkTo(title.bottom)
//                })
//        }


        ConstraintLayout(modifier = modifier.fillMaxWidth()) {
            val (profileImage, author, description) = createRefs()
            AsyncImage(
                model = cardData.imageUri,
                contentDescription = cardData.imageDescription,
                contentScale = ContentScale.Crop,
                placeholder = ColorPainter(color = placeHolderColor),
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
                    .constrainAs(profileImage) {

                        centerVerticallyTo(parent)
                        start.linkTo(parent.start, margin = 8.dp)

//                        linkTo(parent.top, parent.bottom)

//                        top.linkTo(parent.top)
//                        bottom.linkTo(parent.bottom)
                    }
            )
            Text(
                text = cardData.author,
                modifier = Modifier.constrainAs(author) {
                    linkTo(profileImage.end, parent.end, startMargin = 8.dp, endMargin = 8.dp)
                    width = Dimension.fillToConstraints
                }
            )
            Text(
                text = cardData.description,
                modifier = Modifier.constrainAs(description) {
                    linkTo(profileImage.end, parent.end, startMargin = 8.dp, endMargin = 8.dp)
                    width = Dimension.fillToConstraints
                }
            )
// 어떤 위젯을 묶을지 먼저 고민하고 chain 으로 묶은 후 배치해야 좀 더 생각하기 편할 듯
            val chain = createVerticalChain(author, description, chainStyle = ChainStyle.Packed)
            constrain(chain) {
                top.linkTo(parent.top, margin = 8.dp)
                bottom.linkTo(parent.bottom, margin = 8.dp)
            }
        }
    }

//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier.padding(8.dp)
//        ) {
//            AsyncImage(
//                model = cardData.imageUri,
//                contentDescription = cardData.imageDescription,
//                contentScale = ContentScale.Crop,
//                placeholder = ColorPainter(color = placeHolderColor),
//                modifier = Modifier
//                    .clip(CircleShape)
//                    .size(40.dp)
//            )
//            Spacer(modifier = Modifier.size(8.dp))
//            Column {
//                Text(text = cardData.author)
//                Text(text = cardData.description)
//            }
//        }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    Ch04ProjectTheme {
        Column {
            Greeting4(cardData = cardData)
        }
    }
}


data class CardData(
    val imageUri: String,
    val imageDescription: String,
    val author: String,
    val description: String
)

val cardData = CardData(
    imageUri = "https://raw.githubusercontent.com/Fastcampus-Android-Lecture-Project-2023/part4-chapter3/main/part4-chapter3-10/app/src/main/res/drawable-xhdpi/wall.jpg",
    imageDescription = "엔텔로프 캐년",
    author = "Dalinaum",
    description = "엔텔로프 캐년은 죽기 전에 꼭 봐야할 절경으로 소개되었습니다."
)