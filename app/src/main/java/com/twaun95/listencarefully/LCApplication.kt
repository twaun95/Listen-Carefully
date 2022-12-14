package com.twaun95.listencarefully

import android.app.Application
import com.twaun95.listencarefully.di.modules.HandlerModule
import com.twaun95.listencarefully.di.modules.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class LCApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@LCApplication)
            modules(
                HandlerModule.module,
                ViewModelModule.module
            )
        }
    }
}