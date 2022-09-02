package com.twaun95.listencarefully.presentation.ui.soundeffect

import com.twaun95.listencarefully.base.BaseViewModel
import com.twaun95.listencarefully.manager.sound_effect.Effect
import com.twaun95.listencarefully.manager.sound_effect.SoundEffectManager

class SoundEffectActivityViewModel(
    private val soundEffectManager: SoundEffectManager
) : BaseViewModel() {

    init {
        soundEffectManager.initialize()
    }

    fun soundEffectButton() {
        soundEffectManager.play(Effect.BUTTON_SOUND)
    }

    fun soundEffectCorrect() {
        soundEffectManager.play(Effect.CORRECT)
    }

    fun release(){
        soundEffectManager.release()
    }

}