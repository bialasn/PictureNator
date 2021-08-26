package com.nbprog.picturenator

import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setOnClicks()
        setPathFromCapturedAndSavedImage()
    }

    private fun setOnClicks() {
        mainAccTakeFromGalleryBtn.setOnClickListener {
            TestowaKlasa().getImageFromGallery(applicationContext)
        }
        mainAccOpenCameraBtn.setOnClickListener {
            TestowaKlasa().openCameraAndTakePhoto(applicationContext)
        }
    }

    private fun setPathFromCapturedAndSavedImage() {
        mainAccPathTxt.text = "Tu będzie path"
    }


    /**
     *  Tylko do demo ustawimy sobie zrobioną fotkę na ekranie
     * */
    private fun setSavedImageFromLibraryFun(image: Bitmap) {
        TestowaKlasa().getImageFromPath("folder/folder/02390f00f.jpg")
        mainAccImageCaptured.setImageBitmap(image)
    }

}