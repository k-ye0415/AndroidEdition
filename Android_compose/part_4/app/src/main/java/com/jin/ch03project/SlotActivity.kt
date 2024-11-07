package com.jin.ch03project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jin.ch03project.ui.theme.Ch03ProjectTheme

class SlotActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ch03ProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting8(

                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// 스텝 1: `Row`를 `@Composable` 함수로 분리합시다.
// `checked`의 값, `Text`의 `text`를 인자로 전달합시다.
//@Composable
//fun CheckboxWithText(checked: MutableState<Boolean>, text:String) {
//    Row(verticalAlignment = Alignment.CenterVertically) {
//        Checkbox(checked = checked.value, onCheckedChange = { checked.value = it })
//        Text(text, modifier = Modifier.clickable { checked.value = !checked.value })
//    }
//}

// 스텝 2: `@Composable` 함수에서 `@Composable () -> Unit` 타입으로
// `content`를 받아옵시다. `Row`의 `Text`를 뺴고 `content()`를 넣읍시다.
// `Row`에 `Modifier.clickable`를 넣어 전체를 클릭가능하게 합시다.
//@Composable
//fun CheckboxWithText(checked: MutableState<Boolean>, content: @Composable () -> Unit) {
//    Row(verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier.clickable {
//            checked.value = !checked.value
//        }) {
//        Checkbox(checked = checked.value, onCheckedChange = { checked.value = it })
//        content() // 해당 함수에서 구현을 하지않고, 함수를 호출하는 곳에서 구현.
//    }
//}

// 스텝 3: `content`의 타입을 `@Composable RowScope.() -> Unit`로
// 바꿉시다. 이렇게 다른 콤포저블 컨텐트를 넣는 방식을 Slot API라 합니다.
//@Composable
//fun CheckboxWithText(checked: MutableState<Boolean>, content: @Composable RowScope.() -> Unit) {
//    // row 를 사용하기때문에 함수를 사용하는 곳에도 RowScope 를 사용할 수 있도록 보여줌.
//    Row(verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier.clickable {
//            checked.value = !checked.value
//        }) {
//        Checkbox(checked = checked.value, onCheckedChange = { checked.value = it })
//        content() // 해당 함수에서 구현을 하지않고, 함수를 호출하는 곳에서 구현.
//    }
//}

// 스텝 4: 상태를 바꾸는 람다를 `@Composable` 함수의 인자로 뺍시다.
// 인자에서 MutableState대신 boolean 값으로 변경합시다.
@Composable
fun CheckboxWithText(
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

@Composable
fun Greeting8(modifier: Modifier = Modifier) {
    val check1 = remember { mutableStateOf(false) }
    val check2 = remember { mutableStateOf(false) }
    Column(
        modifier
    ) {
//        CheckboxWithText(check1, "집가고 싶당@!")
//        CheckboxWithText(check2, "dsahgoiewhgoiewhioehio")

//        CheckboxWithText(check1) {
//            Text("음? 오호.....")
//        }

        CheckboxWithText(checked = check1.value, onCheckedChanged = {
            check1.value = !check1.value
        }) {
            Text("음? 오호.....")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview12() {
    Ch03ProjectTheme {
        Greeting8()
    }
}