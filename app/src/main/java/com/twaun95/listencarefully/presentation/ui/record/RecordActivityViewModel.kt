package com.twaun95.listencarefully.presentation.ui.record

import androidx.lifecycle.MutableLiveData
import com.twaun95.listencarefully.base.BaseViewModel

class RecordActivityViewModel(
    private val recordHandler: RecordHandler
) : BaseViewModel() {

    val recordState = MutableLiveData(State.IDLE)

    init {
        recordHandler.initialize()
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
        recordState.value = State.ON_PLAY
    }

    fun stopPlay() {
        recordState.value = State.READY_PLAY
    }

    fun reset() {
        recordState.value = State.IDLE
    }

}