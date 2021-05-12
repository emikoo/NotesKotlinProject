package com.example.notesapp

import android.app.Application
import com.example.notesapp.di.networkRepository
import com.example.notesapp.di.repositoryModule
import com.example.notesapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            inject()
        }
    }

    fun inject() = loadKoinModules

    private val loadKoinModules by lazy {
        loadKoinModules(listOf(viewModelModule, repositoryModule, networkRepository))
    }
}