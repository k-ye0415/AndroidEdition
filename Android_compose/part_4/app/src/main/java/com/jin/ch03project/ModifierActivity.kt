package com.jin.ch03project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jin.ch03project.ui.theme.Ch03ProjectTheme

class ModifierActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ch03ProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ModifierExample(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ModifierExample(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
//        Button(onClick = {}, modifier = Modifier.fillMaxSize()) {
//            Icon(imageVector = Icons.Filled.Search, contentDescription = null)
//            Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing))
//            Text("Search")
//        }

        Button(onClick = {}, modifier = Modifier.height(100.dp)) {
            Icon(imageVector = Icons.Filled.Search, contentDescription = null)
            Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing))
            Text("Search")
        }

        Surface {  }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    Ch03ProjectTheme {
        ModifierExample()
    }
}