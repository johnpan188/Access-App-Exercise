package app.com.access

import android.app.Application
import com.mikepenz.iconics.Iconics
import com.mikepenz.iconics.typeface.library.googlematerial.GoogleMaterial

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Iconics.init(applicationContext)
        Iconics.registerFont(GoogleMaterial)
    }
}