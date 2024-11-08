package com.jin.ch03project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jin.ch03project.ui.theme.Ch03ProjectTheme

class ScaffoldActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ch03ProjectTheme {
                Greeting9()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting9() {
    var check by remember { mutableStateOf(false) }
    //modifier = Modifier.fillMaxSize(),
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Image(Icons.Filled.KeyboardArrowLeft, contentDescription = null)
                    }
                },
                title = {
                    Text("HELLO")
                }
            )
        },
        floatingActionButton = {
            IconButton(onClick = {}) {
                Image(Icons.Filled.KeyboardArrowLeft, contentDescription = null)
            }
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            CustomCheckbox(checked = check, onCheckedChanged = {
                check = !check
            }) {
                Text("졸리군")
            }
        }
    }
}

@Composable
fun CustomCheckbox(
    checked: Boolean,
    onCheckedChanged: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    // row 를 사용하기때문에 함수를 사용하는 곳에도 RowScope 를 사용할 수 있도록 보여줌.
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            onCheckedChanged()
        }) {
        Checkbox(checked = checked, onCheckedChange = { onCheckedChanged() })
        content() // 해당 함수에서 구현을 하지않고, 함수를 호출하는 곳에서 구현.
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview13() {
    Ch03ProjectTheme {
        Greeting9()
    }
}