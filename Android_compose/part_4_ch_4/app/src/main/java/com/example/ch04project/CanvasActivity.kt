package com.example.ch04project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ch04project.ui.theme.Ch04ProjectTheme

class CanvasActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ch04ProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting5(

                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting5(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(20.dp)) {
        // 단계 1: `drawLine`을 사용해봅시다. 파라미터로 색상, 시작(`Offset`)
        // 끝(`Offset` 타입)을 받습니다.
        drawLine(Color.Red, Offset(10f,10f), Offset(20f,10f))

        // 단계 2: `drawCircle`을 사용해보세요. 색상, 반지름, 중앙(`Offset`)

        // 단계 3: 아래의 규칙으로 그려진 아이콘 `Icons.Filled.Send`를
        // `drawLine`으로 변경해봅시다.

        // ImageVector에서는 한붓 그리기 처럼 연속으로 그려집니다.
        // `moveTo`로 2.01f, 21.0f로 이동한 후 거기에서
        // 23.0f, 12.0f로 선이 그어지는 식입니다.

        //        moveTo(2.01f, 21.0f)
        //        lineTo(23.0f, 12.0f)
        //        lineTo(2.01f, 3.0f)
        //        lineTo(2.0f, 10.0f)
        //        lineToRelative(15.0f, 2.0f)
        //        lineToRelative(-15.0f, 2.0f)
        //        close()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
    Ch04ProjectTheme {
        Greeting5()
    }
}