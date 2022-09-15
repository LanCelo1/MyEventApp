package uz.gita.myeventapp.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import uz.gita.myeventapp.data.local.MySharedPreferences

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        MySharedPreferences.initSharedPreference(this)
    }
}