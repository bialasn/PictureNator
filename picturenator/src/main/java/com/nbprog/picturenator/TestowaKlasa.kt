package com.nbprog.picturenator

import android.content.Context
import android.widget.Toast

class TestowaKlasa {

    /**
     *  Ogólne fun:
     *
     *  Otworzenie galerii i pobranie zdj i zapis w folderze i zwrotka uri/patha
     *  Otworzenie camery i po zrobieniu zdj zapis w folderze i zwrotka uri/patha
     *  Pobranie fotki w formie bitmapy po podaniu patha/uri
     *
     *  Powinno działać tak samo na kazdym urządzeniu
     *  Przyjmujemy że nazwa zdj to timestamp, będzie uniwersalnie
     *
     *  Na potem do przemyślenia:
     *  Ustawienie jakości zapisanego zdjęcia
     *  Ustawienie nazwy folderu
     * */

    /**
     *  Otwieramy galerię, po wybraniu zdjęcia sprawdzamy czy folder o takiej nazwie istnieje  jak nie to robimy a jak tak to zapisujemy do niego wybrane zdj z galerii i zwracamy path do niego
     * */
    fun getImageFromGallery(context: Context): String {
        Toast.makeText(context, "Open gallery", Toast.LENGTH_SHORT).show()
        return ""
    }

    /**
     *  Otwieramy camere, po zrobieniu zdjęcia sprawdzamy czy folder o takiej nazwie istnieje jak nie to robimy a jak tak to  zapisujemy  i zwracamy path do niego
     * */
    fun openCameraAndTakePhoto(context: Context): String {
        Toast.makeText(context, "Open gallery", Toast.LENGTH_SHORT).show()
        return ""
    }


    /**
     *  Funkcja którą odpalamy obojętnie gddzie w apce podając path i mamy z niego zdjęcie
     *  Powinno zwracać bitmapę
     * */
    fun getImageFromPath(path: String) {
    }
}