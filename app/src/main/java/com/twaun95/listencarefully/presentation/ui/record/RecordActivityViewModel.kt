package com.twaun95.listencarefully.presentation.ui.record

import androidx.lifecycle.MutableLiveData
import com.twaun95.listencarefully.base.BaseViewModel
import com.twaun95.listencarefully.manager.sound.singleton.SoundManager

class RecordActivityViewModel(
    private val recordHandler: RecordHandler,
    private val soundHandler: SoundManager
) : BaseViewModel() {

    val recordState = MutableLiveData(State.IDLE)
    private var soundLoadId = SoundManager.INVALID_LOAD_ID
    private val endListener = { recordState.value = State.READY_PLAY }

    init {
        recordHandler.initialize()
    }

    fun getAmplitude() :Int {
        return recordHandler.getDecibelValue()
    }

    fun startRecord() {
        recordState.value = State.ON_RECORD
        recordHandler.loadFile()
        recordHandler.startRecord()
    }

    fun stopRecord() {
        recordHandler.stopRecord()
        recordState.value = State.READY_PLAY
    }

    fun startPlay() {
        if (soundLoadId != SoundManager.INVALID_LOAD_ID) {
            soundHandler.play(soundLoadId, 0, endListener)
            recordState.value = State.ON_PLAY
        } else {
            soundHandler.loadFile { id ->
                soundLoadId = id
                soundHandler.play(soundLoadId, 0, endListener)
                recordState.value = State.ON_PLAY
            }
        }
    }

    fun stopPlay() {
        soundHandler.pause(soundLoadId)
        recordState.value = State.READY_PLAY
    }

    fun reset() {
        recordHandler.stopRecord()
        soundHandler.pause(soundLoadId)
        recordState.value = State.IDLE
    }

    fun release() {
        soundHandler.release(soundLoadId)
        recordHandler.release()
    }
}