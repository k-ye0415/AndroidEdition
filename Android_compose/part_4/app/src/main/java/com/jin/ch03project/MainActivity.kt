package com.jin.ch03project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jin.ch03project.ui.theme.Ch03ProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ch03ProjectTheme {
//                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                Greeting(
                    name = "Android",
//                        modifier = Modifier.padding(innerPadding)
                )
//                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello, $name world!",
//        color = Color.Green
        color = Color(0xffff99bb),
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Cursive, // font 바꾸기
        letterSpacing = 2.sp, // 글짜 간격
        maxLines = 2,
        textDecoration = TextDecoration.Underline,
        textAlign = TextAlign.Center,
        modifier = Modifier.width(500.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ch03ProjectTheme {
        Greeting("Android")
    }
}