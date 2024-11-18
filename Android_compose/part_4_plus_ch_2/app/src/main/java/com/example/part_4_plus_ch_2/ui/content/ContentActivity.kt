package com.example.part_4_plus_ch_2.ui.content

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class ContentActivity:ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            ContentScreen(intent.getIntExtra("id", 0))
        }
    }
}