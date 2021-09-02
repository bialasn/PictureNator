# PictureNator
Library for downloading photos from gallery and camera.


build.gradle(module:app)

      allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }  <- this line
    }
}

Actual Version :

       implementation 'com.github.bialasn:PictureNator:v.1.0.0'

Add to manifest :

      <provider
            android:name="androidx.core.content.FileProvider"
            android:authorities="${applicationId}.provider"
            android:grantUriPermissions="true"
            android:exported="false">
            <meta-data
                android:name="android.support.FILE_PROVIDER_PATHS"
                android:resource="@xml/file_paths" />
        </provider>
        
Add res/xml : 

      <?xml version="1.0" encoding="utf-8"?>
      <paths>
          <cache-path
              name="cached_files"
              path="." />
          <files-path
              name="images"
              path="." />
      </paths>
      
      
How to use: 

      L00k into MainActivity
