package com.twaun95.listencarefully.manager.sound_effect

import android.content.Context

class SoundEffectManager(private val androidContext: Context) {

    private val queue = mutableListOf<SoundEffect>()

    fun initialize() {
        queue.add(SoundEffect().apply { this.create() })
    }

    fun play(soundEffectType : Effect) {
        queue.last().soundPlay(androidContext, soundEffectType)
    }

    fun release() {
        queue.removeFirst().delete()
    }
}