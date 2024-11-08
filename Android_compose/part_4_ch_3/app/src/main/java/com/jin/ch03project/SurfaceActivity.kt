package com.jin.ch03project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jin.ch03project.ui.theme.Ch03ProjectTheme

class SurfaceActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ch03ProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting2(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Surface(
            modifier = Modifier.padding(5.dp),
//            tonalElevation = 10.dp, // foreground
            shadowElevation = 5.dp // background
        ) {
            Text(
                text = "Hello $name!",
                modifier = Modifier.padding(5.dp),
            )
        }
        Surface(
            border = BorderStroke(width = 2.dp, color = Color.Cyan),
            modifier = Modifier.padding(5.dp),
//            tonalElevation = 10.dp, // foreground
            shadowElevation = 5.dp // background
        ) {
            Text(
                text = "Hello $name!",
                modifier = Modifier.padding(5.dp),
            )
        }

        Surface(
            border = BorderStroke(width = 2.dp, color = Color.Cyan),
            modifier = Modifier.padding(5.dp),
//            tonalElevation = 10.dp, // foreground
            shadowElevation = 5.dp, // background
            shape = CircleShape,
            color = MaterialTheme.colorScheme.error, // 자동으로 안에 있는 content 의 색상을 변경해줌
            // 만약 안에 있는 색상을 변경해주고싶으면
            contentColor = Color.Yellow, // 해당 속성을 지니면 됨.
        ) {
            Icon(imageVector = Icons.Filled.Search, contentDescription = null)
//            Text(
//                text = "Hello $name!",
//                modifier = Modifier.padding(5.dp),
//            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    Ch03ProjectTheme {
        Greeting2("Android")
    }
}