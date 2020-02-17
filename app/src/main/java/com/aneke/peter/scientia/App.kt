package com.aneke.peter.scientia

import android.app.Application
import com.aneke.peter.scientia.di.dataModule
import com.aneke.peter.scientia.di.repositories
import com.aneke.peter.scientia.di.viewModels
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        insertKoin()
    }

    private fun insertKoin() =
        startKoin {
            androidContext(this@App)
            modules(listOf(viewModels, dataModule, repositories))
        }
}