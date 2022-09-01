package com.twaun95.listencarefully.di.modules

import com.twaun95.listencarefully.presentation.ui.main.MainActivityViewModel
import com.twaun95.listencarefully.presentation.ui.record.RecordActivityVIewModel
import com.twaun95.listencarefully.presentation.ui.sound.SoundActivityViewModel
import com.twaun95.listencarefully.presentation.ui.soundeffect.SoundEffectActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object ViewModelModule : KoinModule{
    override val module: Module
        get() = module {
            viewModel { MainActivityViewModel() }
            viewModel { SoundActivityViewModel() }

        }
}