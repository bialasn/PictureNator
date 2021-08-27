package com.nbprog.picturenator

import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import java.io.File
import java.lang.ref.WeakReference

class GetPictureFromGallery(activity: ComponentActivity, val callback: (File) -> Unit) {

    private val weakReferenceToActivity = WeakReference(activity)

    private val launcher: ActivityResultLauncher<String>? =
        weakReferenceToActivity.get()
            ?.registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
                uri?.let {
                    it.path?.let { pathNotNull ->
                        callback(File(pathNotNull))
                    }
                }
            }

    fun getPicture() {
        launcher?.launch("image/*")
    }

}