package com.nbprog.picturenator.extension

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri

fun Uri.toBitmap(contentResolver: ContentResolver): Bitmap? {
    val imageStream = contentResolver.openInputStream(this)
    return BitmapFactory.decodeStream(imageStream)
}