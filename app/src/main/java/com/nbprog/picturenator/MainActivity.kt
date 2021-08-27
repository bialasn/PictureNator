package com.nbprog.picturenator

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts.TakePicture
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val AVATAR_FILENAME_PREFIX = "avatar"
    val AVATAR_EXT = ".jpeg"

    var uriToSavePictureFromCamera: Uri? = null

    private val takeImageResult = registerForActivityResult(TakePicture()) { isSuccess ->
        if (isSuccess) {
            uriToSavePictureFromCamera?.let {
                val imageStream = contentResolver.openInputStream(it)
                val selectedImage = BitmapFactory.decodeStream(imageStream)
                val bitmapFileModel = FileHelper.BitmapFileModel(
                    this.cacheDir, selectedImage, AVATAR_FILENAME_PREFIX, AVATAR_EXT
                )
            }
        }
    }

    val imageFromGalleryResult = GetPictureFromGallery(this) {
        setPathFromCapturedAndSavedImage(it.absolutePath)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setOnClicks()
        setPathFromCapturedAndSavedImage("kkkkkk")
        lifecycle
    }

    private fun setOnClicks() {
        mainAccTakeFromGalleryBtn.setOnClickListener {
            val tempFileToSavePictureFromCamera = FileHelper.createFile(filesDir)
            uriToSavePictureFromCamera =
                FileProvider.getUriForFile(
                    this,
                    "${BuildConfig.APPLICATION_ID}.provider",
                    tempFileToSavePictureFromCamera
                )
            takeImageResult.launch(uriToSavePictureFromCamera)
        }
        mainAccOpenCameraBtn.setOnClickListener {
            imageFromGalleryResult.getPicture()
        }
    }

    private fun setPathFromCapturedAndSavedImage(path: String) {
        mainAccPathTxt.text = path
    }


    /**
     *  Tylko do demo ustawimy sobie zrobioną fotkę na ekranie
     * */
    private fun setSavedImageFromLibraryFun(image: Bitmap) {
        TestowaKlasa().getImageFromPath("folder/folder/02390f00f.jpg")
        mainAccImageCaptured.setImageBitmap(image)
    }

}