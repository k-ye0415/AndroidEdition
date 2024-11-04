package com.jin.ch03project

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jin.ch03project.ui.theme.Ch03ProjectTheme

class ButtonActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ch03ProjectTheme {
                Scaffold { paddingValues -> // flutter 처럼 화면 지정할때 필요한거임. 이게 없으면 status bar 까지 영역을 차지하기 때문.
                    ButtonExample(
                        modifier = Modifier.padding(paddingValues),
                        onButtonClicked = {
                            // callback 받아 toast 띄우기
                            Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show()
                        })
                }
            }
        }
    }
}


@Composable
fun ButtonExample(modifier: Modifier = Modifier, onButtonClicked: () -> Unit) {
    Column {
        Button(onClick = onButtonClicked, modifier = modifier) {
            // Button 내에 icon
            Icon(
                imageVector = Icons.Filled.Done,
                contentDescription = null,
            )
            // android 내에 있는 icon 여백
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Send")
        }

        Button(onClick = onButtonClicked, modifier = modifier, enabled = false) {
            // Button 내에 icon
            Icon(
                imageVector = Icons.Filled.Clear,
                contentDescription = null,
            )
            // android 내에 있는 icon 여백
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Button disEnabled")
        }

        Button(
            onClick = onButtonClicked, modifier = modifier, border = BorderStroke(2.dp, Color.Black)
        ) {
            // Button 내에 icon
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null,
            )
            // android 내에 있는 icon 여백
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Button border")
        }

        Button(
            onClick = onButtonClicked, modifier = modifier, border = BorderStroke(2.dp, Color.Black),
            shape = RoundedCornerShape(10)
        ) {
            // Button 내에 icon
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null,
            )
            // android 내에 있는 icon 여백
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Button Shape")
        }

        Button(
            onClick = onButtonClicked, modifier = modifier, border = BorderStroke(2.dp, Color.Black),
            shape = CircleShape,
            contentPadding = PaddingValues(30.dp)
        ) {
            // Button 내에 icon
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null,
            )
            // android 내에 있는 icon 여백
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Button Shape")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    Ch03ProjectTheme {
        ButtonExample(onButtonClicked = {})
    }
}