package com.abaffym.moviesapp.extension

import android.support.design.widget.TabLayout

fun TabLayout.addOnTabSelected(body: (Int) -> Any) {
    addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(tab: TabLayout.Tab) {

        }

        override fun onTabUnselected(tab: TabLayout.Tab) {
        }

        override fun onTabSelected(tab: TabLayout.Tab) {
            body(tab.position)
        }
    })
}
