package com.example.part_4_plus_ch_2.ui.home

import android.content.Intent
import com.example.part_4_plus_ch_2.ui.content.ContentActivity

class MainState(val activity:MainActivity) {
    fun showContent(index:Int) {
        activity.startActivity(Intent(activity, ContentActivity::class.java).apply {
            putExtra("id", index)
        })
    }
}