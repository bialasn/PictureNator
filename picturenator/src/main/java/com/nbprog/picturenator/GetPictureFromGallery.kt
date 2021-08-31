package com.nbprog.picturenator

import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.*
import com.nbprog.picturenator.extension.toBitmap
import java.io.File
import java.lang.ref.WeakReference

class GetPictureFromGallery(activity: ComponentActivity, val callback: (File) -> Unit) {

    private val weakReferenceToActivity = WeakReference(activity)

    private val activityFromWR: ComponentActivity?
        get() = weakReferenceToActivity.get()

    private val launcher: ActivityResultLauncher<String>? =
        activityFromWR?.registerForActivityResult(
            GetContent()
        ) { uri ->
            val notNullUri = uri ?: return@registerForActivityResult
            val notNullActivity = activityFromWR ?: return@registerForActivityResult
            val fileToSaveTakenImage = FileHelper.createFile(notNullActivity.filesDir)
            val bitmapFromUri =
                notNullUri.toBitmap(notNullActivity.contentResolver)
                    ?: return@registerForActivityResult
            val savedBitmapInFile =
                FileHelper.saveBitmapIntoFile(bitmapFromUri, fileToSaveTakenImage)
            callback(savedBitmapInFile)
        }

    fun getPicture() {
        launcher?.launch("image/*")
    }
}