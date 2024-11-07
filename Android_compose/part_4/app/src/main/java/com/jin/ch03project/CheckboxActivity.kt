package com.jin.ch03project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jin.ch03project.ui.theme.Ch03ProjectTheme

class CheckboxActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ch03ProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting5(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable // composable 은 언제 다시 새롭게 그려질지 모르기때문에 데이터를 초기화 하거나 프로퍼티를 선언하는건 적합하지 않다.
fun Greeting5(modifier: Modifier = Modifier) {
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
//        val isChecked = remember { mutableStateOf(false) } // 값을 기억한다.
//        Checkbox(checked = isChecked.value, onCheckedChange = {
//            isChecked.value = !isChecked.value
//        })
//        Text("안녕!")

//        var isChecked by remember { mutableStateOf(false) }
//        Checkbox(checked = isChecked, onCheckedChange = {
//            isChecked = !isChecked
//        })
//        Text("안녕!")

        // 비구조화
//        val (isChecked, setChecked) = remember { mutableStateOf(false) }
//        Checkbox(checked = isChecked, onCheckedChange = {
//            setChecked(!isChecked)
//        })
//        Text("안녕!")

        // 더 간결하게
        val (isChecked, setChecked) = remember { mutableStateOf(false) }
        Checkbox(
            checked = isChecked,
            onCheckedChange = setChecked
        )
        Text("안녕!", modifier = Modifier.clickable {
            setChecked(!isChecked)
        })

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview9() {
    Ch03ProjectTheme {
        Greeting5()
    }
}