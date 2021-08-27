package com.nbprog.picturenator

import android.graphics.Bitmap
import com.nbprog.picturenator.extension.toOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

object FileHelper {

    fun createFile(directory: File, prefix: String? = null, suffix: String? = null): File {
        val timeStamp = SimpleDateFormat.getDateTimeInstance().format(Date())
        var fileName = timeStamp
        prefix?.let { fileName = prefix + fileName }
        suffix?.let { fileName += suffix }

        return File(directory, fileName)
    }

    fun saveBitmapIntoFile(bitmapFileModel: BitmapFileModel, destinationFile: File): File {
        val byteArrayOutputStream = bitmapFileModel.bitmap.toOutputStream()
        val fos = FileOutputStream(destinationFile)
        fos.write(byteArrayOutputStream.toByteArray())
        fos.flush()
        fos.close()

        return destinationFile
    }

    fun getFileByPath(path: String): File {
        return File(path)
    }

    data class BitmapFileModel(
        val directory: File,
        var bitmap: Bitmap,
        val prefix: String = "",
        val suffix: String = ""
    )
}