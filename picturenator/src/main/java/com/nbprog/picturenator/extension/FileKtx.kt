package com.nbprog.picturenator.extension

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File

fun File.toBitmap(): Bitmap {
    val filePath = path
    return BitmapFactory.decodeFile(filePath)
}