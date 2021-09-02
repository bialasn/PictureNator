package com.nbprog.picturenator.extension

import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import java.io.File

fun ComponentActivity.getImageFromGallery(callback: (File) -> Unit) {
    val selectImageFromGalleryResult =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
//            val imageStream = contentResolver.openInputStream(it)
//            val selectedImage = BitmapFactory.decodeStream(imageStream)
//            val bitmapFileModel = FileHelper.BitmapFileModel(
//                this.cacheDir, selectedImage, AVATAR_FILENAME_PREFIX, AVATAR_EXT
//            )
                it.path?.let { pathNotNull ->
                    callback(File(pathNotNull))
                }
            }
        }
    selectImageFromGalleryResult.launch("image/*")
}