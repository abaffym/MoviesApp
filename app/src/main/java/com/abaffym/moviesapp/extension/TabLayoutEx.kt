package com.abaffym.moviesapp.extension

import android.support.design.widget.TabLayout

inline fun TabLayout.onTabSelected(crossinline action: (position: Int) -> Unit) {
    addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab) {
            action(tab.position)
        }

        override fun onTabUnselected(tab: TabLayout.Tab) {

        }

        override fun onTabReselected(tab: TabLayout.Tab) {

        }
    })
}
