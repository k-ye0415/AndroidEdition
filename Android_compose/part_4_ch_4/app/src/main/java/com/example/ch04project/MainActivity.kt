package com.example.ch04project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.ch04project.ui.theme.Ch04ProjectTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ch04ProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    ConstraintLayout(modifier.fillMaxSize()) {
        // 단계 2: createRefs()를 이용해서 아래 박스들의 레퍼런스를 가져옵시다.
        // createRefs는 여러개의 레퍼런스를 리턴하니 destruction으로 분해합시다.
        // red, magenta, green, yellow 박스가 있습니다.
        val (redBox, magentaBox, greenBox, yellowBox) = createRefs()

        Box(
            // 단계 3: constraintsAs 모디파이어를 추가하고 레퍼런스를 전달합시다.
            // 후행 람다로 top, start, end, bottom 앵커를 지정하고
            // linkTo 호출합니다.
            // 인자로는 parent의 앵커(top, start, end, bottom)을
            // 전달해봅시다.

            // 단계 4: linkTo의 키워드 인자 margin을 추가합시다.
            modifier = Modifier
                .size(40.dp)
                .background(Color.Red)
                .constrainAs(redBox) {// 해당 위젯이 어디에 위치할지 지정해주는것
                    bottom.linkTo(parent.bottom, margin = 8.dp)// app:layout_constraintBottom_toBottomOf="parent"
                    // bottom 을 어디에 붙이겠다 (어디에)
                    end.linkTo(parent.end, margin = 4.dp)
                }
        )
        Box(
            // 단계 5: 마젠타 박스를 parent의 start와 end에 연결합시다.
            modifier = Modifier
                .size(40.dp)
                .background(Color.Magenta)
                .constrainAs(magentaBox) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        Box(

            modifier = Modifier
                .size(40.dp)
                .background(Color.Green)
                .constrainAs(greenBox) {
                    // 단계 6: 그린 박스를 linkTo를 이용해서 정 가운데로 연결해봅시다.
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                    top.linkTo(parent.top)
//                    bottom.linkTo(parent.bottom)
                    // 단계 7: 앵커 메서드 linkTo 대신에 centerTo 함수를 사용해봅시다.
                    centerTo(parent)
                }
        )

        Box(
            // 단계 8: 옐로 박스를 마젠타 박스 오른쪽 대각선 아래에 위치해봅시다.
            // linkTo를 쓰고 인자로 parent 대신 그린 박스의 레퍼런스를 사용합시다.
            modifier = Modifier
                .size(40.dp)
                .background(Color.Yellow)
                .constrainAs(yellowBox) {
                    top.linkTo(magentaBox.bottom)
                    start.linkTo(magentaBox.end)
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ch04ProjectTheme {
        Greeting()
    }
}