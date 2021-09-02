package com.nbprog.picturenator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.nbprog.picturenator.extension.toBitmap
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val takeImageFromCameraResult = GetPictureFromCamera(this) {
        setPathFromCapturedAndSavedImage(it.absolutePath)
        val bitmap = FileHelper.getBitmapFromPath(it.path)
        mainAccImageCaptured.setImageBitmap(bitmap)
    }

    val imageFromGalleryResult = GetPictureFromGallery(this) {
        setPathFromCapturedAndSavedImage(it.absolutePath)
        val bitmap = FileHelper.getBitmapFromPath(it.path)
        mainAccImageCaptured.setImageBitmap(bitmap)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setOnClicks()
        checkRecentlySavedFiles()
    }

    private fun setOnClicks() {
        mainAccTakeFromGalleryBtn.setOnClickListener {
            takeImageFromCameraResult.getPicture("${BuildConfig.APPLICATION_ID}.provider")
        }
        mainAccOpenCameraBtn.setOnClickListener {
            imageFromGalleryResult.getPicture()
        }
    }

    private fun checkRecentlySavedFiles() {
        val filesInDir = this.filesDir.listFiles()
        filesInDir?.forEach {
            Log.d("FILES", it.name)
        }
        val takeTwoWithLastModified = filesInDir?.sortedByDescending { it.lastModified() }?.take(2)
        if (takeTwoWithLastModified.isNullOrEmpty().not()) {
            recentlySavedPicture1?.setImageBitmap(takeTwoWithLastModified?.first()?.toBitmap())
            recentlySavedPicture2?.setImageBitmap(takeTwoWithLastModified?.last()?.toBitmap())
        }
    }

    private fun setPathFromCapturedAndSavedImage(path: String) {
        mainAccPathTxt.text = path
    }
}