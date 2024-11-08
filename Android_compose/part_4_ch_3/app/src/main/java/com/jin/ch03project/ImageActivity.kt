package com.jin.ch03project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jin.ch03project.ui.theme.Ch03ProjectTheme

class ImageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ch03ProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting3(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting3(modifier: Modifier = Modifier) {
    Column(modifier) {
        Box(modifier = Modifier.size(300.dp), ) {
            Image(contentDescription = "랄랄라~", painter = painterResource(R.drawable.test_image))
        }

        Box(modifier = Modifier.size(300.dp), ) {
            Image(contentDescription = "랄랄라~", imageVector = Icons.Filled.Settings)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview6() {
    Ch03ProjectTheme {
        Greeting3()
    }
}