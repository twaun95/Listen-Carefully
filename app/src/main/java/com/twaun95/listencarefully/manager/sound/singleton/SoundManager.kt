package com.twaun95.listencarefully.manager.sound.singleton

import android.content.Context
import com.twaun95.listencarefully.common.LCLogger

class SoundManager(private val context: Context) {

    companion object {
        private const val MAX_INDEX = 0xFF * 0xFF - 1 // :)
        const val INVALID_LOAD_ID = -1
        const val SOUND_BUTTON_URL_INDEX = 0
        const val RECORD_FILE_NAME = "record_file.acc"
    }

    private val map = mutableMapOf<Int, SoundPlayer>()
    private var indexKey = -1

    fun load(url: String, prepareListener: ((loadId: Int) -> Unit)?): Int {
        return load(listOf(url), prepareListener)
    }

    fun load(url: List<String>, prepareListener: ((loadId: Int)->Unit)? = null) : Int {
        val player = SoundPlayer(context)
        indexKey = (++indexKey)% MAX_INDEX
        val tempIndexKey = indexKey
        map[tempIndexKey] = player

        LCLogger.d("soundmanager manager soundLoadIndex: $tempIndexKey")

        player.setData(url) {
            prepareListener?.invoke(tempIndexKey)
        }
        return indexKey
    }

    fun loadFile(prepareListener: ((loadId: Int) -> Unit)? = null) : Int {
        val player = SoundPlayer(context)

        indexKey = (++indexKey)% MAX_INDEX
        val tempIndexKey = indexKey
        map[tempIndexKey] = player

        player.setDataFile(context.filesDir.absolutePath + RECORD_FILE_NAME) {
            prepareListener?.invoke(tempIndexKey)
        }

        return indexKey
    }

    fun getSoundPlayer(loadId: Int) : SoundPlayer? = map[loadId]

    fun play(loadId: Int, urlIndex: Int, soundEndListener: (()->Unit)? = null) {
        map.forEach { it.value.pausePlayer() }

        LCLogger.d("soundmanager manager play :${map[loadId]} / index: $loadId")

        map[loadId]?.playPlayer(urlIndex, soundEndListener)
    }

    fun pause() {
        map[indexKey]?.pausePlayer()
    }

    fun pause(loadId: Int) {
        map[loadId]?.pausePlayer()
    }

    fun release(loadId: Int) {
        LCLogger.d("soundmanager manager release :${map[loadId]} / index: $loadId")

        map[loadId]?.stopAndReleasePlayer()
        map.remove(loadId)
    }

    fun releaseAll() {
        map.forEach {
            it.value.stopAndReleasePlayer()
        }
        map.clear()
    }

}
