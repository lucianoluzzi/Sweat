package com.lucianoluzzi.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View


fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide(keepSize: Boolean = false) {
    visibility = if (keepSize) View.INVISIBLE else View.GONE
}

fun View.isVisible(): Boolean {
    return visibility == View.VISIBLE
}

fun View.toBitmap(): Bitmap {
    val returnedBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(returnedBitmap)
    val backgroundDrawable: Drawable? = background

    backgroundDrawable?.draw(canvas)
    draw(canvas)

    return returnedBitmap
}