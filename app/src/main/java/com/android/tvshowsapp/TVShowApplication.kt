package com.android.tvshowsapp

import android.app.Application
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.android.tvshowsapp.util.ThemeProvider
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TVShowApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val theme = ThemeProvider(this).getThemeFromPreferences()
        AppCompatDelegate.setDefaultNightMode(theme)
    }
}