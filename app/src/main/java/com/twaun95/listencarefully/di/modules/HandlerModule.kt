package com.twaun95.listencarefully.di.modules

import com.twaun95.listencarefully.manager.sound.factory.ExoPlayerFactory
import com.twaun95.listencarefully.manager.sound.singleton.SoundManager
import com.twaun95.listencarefully.manager.sound_effect.SoundEffectManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

object HandlerModule : KoinModule{
    override val module: Module
        get() = module {
            single { SoundEffectManager(androidContext()) }
            factory { ExoPlayerFactory(androidContext()) }
            single { SoundManager(androidContext()) }
        }
}