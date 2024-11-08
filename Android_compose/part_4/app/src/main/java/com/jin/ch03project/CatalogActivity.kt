package com.jin.ch03project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.jin.ch03project.ui.theme.Ch03ProjectTheme

class CatalogActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ch03ProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting10(
                        modifier = Modifier.padding(innerPadding),
                        itemList = items,
                    )
                }
            }
        }
    }
}

@Composable
fun Item(itemData: ItemData) {
    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Image(painter = painterResource(itemData.imageId), contentDescription = itemData.title)
            Spacer(modifier = Modifier.size(8.dp))
            Text(itemData.title, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.size(8.dp))
            Text(itemData.description)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemPreview() {
    Ch03ProjectTheme {
        Item(
            ItemData(
                imageId = R.drawable.a1,
                title = "해변 놀이 공원",
                description = "해변 놀이 공원 설명입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
            )
        )
    }
}


@Composable
fun Greeting10(modifier: Modifier = Modifier, itemList: List<ItemData>) {
    // recyclerView 대채헤서 사용하는 위젯
    LazyColumn(modifier) {
        /// 이건 내가한거,
//        this.items(
//            count = itemList.size,
//            itemContent = {
//                Item(itemList[it])
//            }
//        )
        // 이렇게 줄일 수 있음
        items(itemList) {item ->
            Item(item)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview14() {
    Ch03ProjectTheme {
        Greeting10(itemList = items)
    }
}


data class ItemData(
    @DrawableRes val imageId: Int,
    val title: String,
    val description: String,
)

val items = listOf(
    ItemData(
        imageId = R.drawable.a1,
        title = "해변 놀이 공원",
        description = "해변 놀이 공원 설명입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
    ),
    ItemData(
        imageId = R.drawable.a1,
        title = "캐년",
        description = "미국의 캐년입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
    ),
    ItemData(
        imageId = R.drawable.a1,
        title = "워터월드",
        description = "워터월드입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
    ),
    ItemData(
        imageId = R.drawable.a1,
        title = "미국의 캐년",
        description = "미국의 캐년입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
    ),
    ItemData(
        imageId = R.drawable.a1,
        title = "라스베가스",
        description = "라스베가스입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
    ),
    ItemData(
        imageId = R.drawable.a1,
        title = "호르슈 밴드",
        description = "호르슈 밴드입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
    ),
    ItemData(
        imageId = R.drawable.a1,
        title = "미국의 길",
        description = "미국의 길입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
    ),
    ItemData(
        imageId = R.drawable.a1,
        title = "엔텔로프 캐년",
        description = "엔텔로프 캐년입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
    ),
    ItemData(
        imageId = R.drawable.a1,
        title = "그랜드 캐년",
        description = "그랜드 캐년입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
    ),
)