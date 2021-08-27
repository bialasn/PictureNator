package com.nbprog.picturenator

import android.graphics.Bitmap
import android.util.Size
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

object FileHelper {

    private const val BEST_QUALITY = 100
    private const val MAX_API_SIZE = 1024 * 350
    private const val MIN_WIDTH = 200
    private const val MIN_HEIGHT = 200

    fun createTempFile(directory: File): File {
        val timeStamp = SimpleDateFormat.getDateTimeInstance().format(Date())

        return File.createTempFile(
            "extraaa",
            ".jpeg",
            directory
        )
    }

    fun saveBitmapIntoTempFile(bitmapFileModel: BitmapFileModel): File {
        if (bitmapFileModel.bitmap.width < MIN_WIDTH) {
            bitmapFileModel.bitmap = fitSize(bitmapFileModel.bitmap, bitmapFileModel.size)
        }
        val outputFile = File.createTempFile(
            bitmapFileModel.prefix, bitmapFileModel.suffix,
            bitmapFileModel.directory
        )

        val byteArrayOutputStream =
            compressBitmap(bitmapFileModel.bitmap, bitmapFileModel.maxBytes)
        val fos = FileOutputStream(outputFile)
        fos.write(byteArrayOutputStream.toByteArray())
        fos.flush()
        fos.close()

        return outputFile
    }

    private fun fitSize(bitmap: Bitmap, desiredMinSize: Size): Bitmap {
        val aspectRatio = bitmap.width / bitmap.height.toFloat()
        //Do it only for width, because in most cases demanding min height will be fulfilled
        val width = desiredMinSize.width
        val height = (width / aspectRatio).roundToInt()

        return Bitmap.createScaledBitmap(bitmap, width, height, false)
    }

    private fun compressBitmap(bitmap: Bitmap, maxBytes: Int): ByteArrayOutputStream {
        val bos = ByteArrayOutputStream()
        var currentQuality = BEST_QUALITY
        do {
            bos.flush()
            bos.reset()
            bitmap.compress(Bitmap.CompressFormat.JPEG, currentQuality, bos)
            val currSize = bos.toByteArray().size
            currentQuality -= 5
        } while (currSize >= maxBytes)
        return bos
    }

    data class BitmapFileModel(
        val directory: File,
        var bitmap: Bitmap,
        val prefix: String = "",
        val suffix: String = "",
        val maxBytes: Int = MAX_API_SIZE,
        val size: Size = Size(MIN_WIDTH, MIN_HEIGHT)
    )
}