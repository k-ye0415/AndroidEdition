package com.jin.ch03project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jin.ch03project.ui.theme.Ch03ProjectTheme

class TextFieldActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ch03ProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting6(

                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting6(modifier: Modifier = Modifier) {
    var strValue by remember { mutableStateOf("졸려") }
    Column(modifier) {
        TextField(
            value = strValue,
            label = {
                Text("으아아아아")
            },
            onValueChange = { strValue = it }
        )
        Text("집에 가고싶어요ㅠㅠ.. $strValue")

        Spacer(modifier = Modifier.size(15.dp))
        OutlinedTextField(
            value = strValue,
            label = {
                Text("으아아아아")
            },
            onValueChange = { strValue = it }
        )
        Text("집에 가고싶어요ㅠㅠ.. $strValue")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview10() {
    Ch03ProjectTheme {
        Greeting6()
    }
}