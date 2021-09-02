package com.nbprog.picturenator

import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.TakePicture
import androidx.core.content.FileProvider
import java.io.File
import java.lang.ref.WeakReference

//TODO: Klasy roznia sie tylko parametrem w registerActivityResult, pomyslec nad jakas klasą bazową
class GetPictureFromCamera(activity: ComponentActivity, val callback: (File) -> Unit) {

    private val weakReferenceToActivity = WeakReference(activity)
    private val activityFromWR: ComponentActivity?
        get() = weakReferenceToActivity.get()

    private val launcher: ActivityResultLauncher<Uri>? =
        activityFromWR?.registerForActivityResult(TakePicture()) { isSuccess ->
            if (isSuccess) {
                fileToSaveImageFromCamera?.let { callback(it) }
            }
        }

    private var fileToSaveImageFromCamera: File? = null

    fun getPicture(authorityForFileProvider: String) {
        val notNullActivity = activityFromWR ?: return
        fileToSaveImageFromCamera = FileHelper.createFile(notNullActivity.filesDir)
        fileToSaveImageFromCamera?.let {
            val uriToSaveImageFromCamera =
                FileProvider.getUriForFile(notNullActivity, authorityForFileProvider, it)
            launcher?.launch(uriToSaveImageFromCamera)
        }
    }
}