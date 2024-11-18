package com.example.part_4_plus_ch_2.ui.content

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.part_4_plus_ch_2.R
import com.example.part_4_plus_ch_2.model.memos


private val MinTitleOffset = 20.dp
private val MaxTitleOffset = 100.dp
private val HzPadding = Modifier.padding(horizontal = 24.dp)

@Composable
fun ContentScreen(memoId: Int) {
    val memo = remember(memos) { memos.single { it.id == memoId } }

    Box(Modifier.fillMaxSize()) {
        val scroll = rememberScrollState(0)
        Body(scroll)
        Title(memo.text) { scroll.value }
    }
}


@Composable
private fun Body(scroll: ScrollState) {
    Column(
        modifier = Modifier.background(Color.White)
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(MaxTitleOffset)
        )

        Column(modifier = Modifier.verticalScroll(scroll)) {
            Surface(Modifier.fillMaxWidth()) {
                Column {
                    Spacer(Modifier.height(110.dp))
                    Text(
                        text = stringResource(R.string.detail_placeholder),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Blue,
                        modifier = HzPadding
                    )
                    Spacer(Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
private fun Title(memoText: String, scrollProvider: () -> Int) {
    val maxOffset = with(LocalDensity.current) { MaxTitleOffset.toPx() }
    val minOffset = with(LocalDensity.current) { MinTitleOffset.toPx() }

    Column(
        modifier = Modifier
            .heightIn(min = MaxTitleOffset)
            .offset {
                val offset = (maxOffset - scrollProvider()).coerceAtLeast(minOffset)
                // scroll 이 계속 변하기 때문에 이 부분에서 recomposition 이 생김. 해결 필요.
                /// 해결 -> 람다 형식으로 변경하여 직접적인 값은 Title 함수를 부르는 곳에서 적용하고,
                /// UI를 그릴 시점 즉, Comoposition 에서 딱 한번만 그린 후 람다에서 변경 값으로 인해 layout 을 다시 그린다.
                IntOffset(x = 0, y = offset.toInt())
            }
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Text(text = memoText, style = MaterialTheme.typography.headlineLarge, color = Color.Black, modifier = HzPadding)
        Spacer(Modifier.height(4.dp))
        Text(
            text = "MEMO",
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = HzPadding
        )
        Spacer(Modifier.height(8.dp))
    }
}