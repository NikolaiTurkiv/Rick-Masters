package com.test.rickmasters

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp
import io.realm.Realm

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}
