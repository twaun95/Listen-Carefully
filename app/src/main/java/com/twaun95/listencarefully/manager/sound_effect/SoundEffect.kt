package com.twaun95.listencarefully.manager.sound_effect

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool


class SoundEffect {

    private lateinit var soundPool: SoundPool
    private lateinit var map: MutableMap<Effect, Pair<Int, Int>?>

    companion object {
        const val MAX_STREAM = 10
        const val VOLUME = 0.5f
        const val LOOP = 0
        const val RATE = 1.0f
        const val PRIORITY_PLAY = 0
        const val PRIORITY_LOAD = 1
        const val NOT_PLAYED = -1
    }

    fun create() {
        soundPool =
            SoundPool.Builder()
                .setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_GAME)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build()
                )
                .setMaxStreams(MAX_STREAM)
                .build()
                .apply { setOnLoadCompleteListener { _, soundId, _ -> play(soundId, VOLUME, VOLUME, PRIORITY_PLAY, LOOP, RATE) }}

        map = mutableMapOf<Effect, Pair<Int, Int>?>(
            Pair(Effect.SPLASH, null),
            Pair(Effect.CORRECT, null),
        )
    }

    fun soundPlay(context: Context, effect: Effect) {
        map[effect]?.let { sound ->
            map.put(
                effect,
                Pair(
                    sound.first,
                    soundPool.play(sound.first, VOLUME, VOLUME, PRIORITY_PLAY, LOOP, RATE)
                )
            )

        } ?: run {
            map.put(
                effect,
                Pair(soundPool.load(context, effect.resourceId, PRIORITY_LOAD), NOT_PLAYED)
            )
        }
    }

    fun delete() = soundPool.release()
}