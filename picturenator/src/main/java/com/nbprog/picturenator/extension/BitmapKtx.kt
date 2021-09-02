package com.nbprog.picturenator.extension

import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.Bitmap.CompressFormat.JPEG
import android.graphics.Bitmap.CompressFormat.PNG
import android.graphics.Bitmap.createScaledBitmap
import android.util.Size
import java.io.ByteArrayOutputStream
import kotlin.math.roundToInt

const val BEST_QUALITY_FOR_COMPRESS = 100

fun Bitmap.fitSize(desiredMinSize: Size): Bitmap {
    val aspectRatio = this.width / this.height.toFloat()
    //Do it only for width, because in most cases demanding min height will be fulfilled
    val width = desiredMinSize.width
    val height = (width / aspectRatio).roundToInt()

    return createScaledBitmap(this, width, height, false)
}

fun Bitmap.compressBitmap(
    maxBytes: Int,
    quality: Int = BEST_QUALITY_FOR_COMPRESS
): ByteArrayOutputStream {
    val bos = ByteArrayOutputStream()
    var currentQuality = quality
    do {
        bos.flush()
        bos.reset()
        this.compress(JPEG, currentQuality, bos)
        val currSize = bos.toByteArray().size
        currentQuality -= 5
    } while (currSize >= maxBytes)
    return bos
}

/**
 * TODO : Describe....
 * @param compressFormat  It is demanded by compress function PNG is default because, it
 * doesn't lose quality
 *
 * @return output stream
 */
fun Bitmap.toOutputStream(compressFormat: CompressFormat = PNG): ByteArrayOutputStream {
    val bos = ByteArrayOutputStream()
    compress(PNG, BEST_QUALITY_FOR_COMPRESS, bos)
    return bos
}