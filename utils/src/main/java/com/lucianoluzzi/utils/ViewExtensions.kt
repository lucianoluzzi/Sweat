package com.lucianoluzzi.utils

import android.view.View

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide(keepSize: Boolean = false) {
    visibility = if (keepSize) View.INVISIBLE else View.GONE
}
