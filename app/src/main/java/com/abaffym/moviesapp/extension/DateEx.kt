package com.abaffym.moviesapp.extension

import java.text.SimpleDateFormat
import java.util.*

fun Date.toSimpleString(): String {
    val dateFormat = SimpleDateFormat("dd. MM. yyyy", Locale.getDefault())
    return dateFormat.format(this)
}