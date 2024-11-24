package com.example.part_4_plus_ch_2.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.part_4_plus_ch_2.model.Memo
import com.example.part_4_plus_ch_2.model.memos
import com.example.part_4_plus_ch_2.ui.theme.Part_4_plus_ch_2Theme

@Composable
fun MainScreen(mainState: MainState) {
    Part_4_plus_ch_2Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Surface(
                modifier = Modifier.padding(innerPadding),
                color = MaterialTheme.colorScheme.background
            ) {
                val memoList = remember { memos.sortedBy { it.id }.toMutableStateList() }
                val onClickAction: (Int) -> Unit = {
                    mainState.showContent(it)
                }

                Column {
                    AddMemo(memoList)
                    MemoList(onClickAction, memoList)
                }
            }
        }
    }
}

@Composable
fun AddMemo(memoList: SnapshotStateList<Memo>) {
    val inputValue = remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .padding(all = 16.dp)
            .height(100.dp),
        horizontalArrangement = Arrangement.End
    ) {
        TextField(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            value = inputValue.value,
            onValueChange = { textFieldValue -> inputValue.value = textFieldValue }
        )
        Button(
            onClick = {
                memoList.add(index = 0, Memo(memoList.size, inputValue.value))
                inputValue.value = ""
            },
            modifier = Modifier
                .wrapContentWidth()
                .fillMaxHeight()
        ) {
            Text("ADD")
        }
    }

}

@Composable
fun ColumnScope.MemoList(onClickAction: (Int) -> Unit, memoList: SnapshotStateList<Memo>) {
    // column 을 사용해서 List 를 그릴 때 이런 방식으로도 가능하다.
    // 중요한 것은 key 라는 키워드를 가지고 모든 list 를 새롭게 그리는 것이 아닌, 새롭게 추가된 부분에 있어서 key 로 mapping 처리 후 해당 item 만 새롭게 그린다.
//    Column {
//        for (memo in memoList) {
//            key(memo.id) {
//                Card(
//                    modifier = Modifier
//                        .height(100.dp)
//                        .background(Color.White)
//                        .padding(horizontal = 16.dp, vertical = 8.dp)
//                        .fillMaxWidth(),
//                    onClick = {
//                        onClickAction(memo.id)
//                    }
//                ) {
//                    Text(text = memo.text, modifier = Modifier.fillMaxSize())
//                }
//            }
//        }
//    }
    // lazyColumn ㅅㅏ용
    LazyColumn(
        modifier = Modifier
            .weight(1f)
    ) {
        items(
            items = memoList,
            key = { it.id }
        ) { memo ->
            Card(
                modifier = Modifier
                    .height(100.dp)
                    .background(Color.White)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth(),
                onClick = {
                    onClickAction(memo.id)
                }
            ) {
                Text(text = memo.text, modifier = Modifier.fillMaxSize())
            }
        }
    }
}