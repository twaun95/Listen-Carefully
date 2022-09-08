package com.twaun95.listencarefully.presentation.ui.video

import androidx.lifecycle.MutableLiveData
import com.twaun95.listencarefully.base.BaseViewModel
import com.twaun95.listencarefully.manager.sound.singleton.SoundManager

class VideoActivityViewModel(
    private val soundManager: SoundManager
) : BaseViewModel() {

    val video_id = MutableLiveData<Int>()


    init {
        isLoading.value = true
    }

    fun initialize() {
        soundManager.load("https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4") {
            video_id.postValue(it)
            isLoading.postValue(true)
        }
    }

    fun release() {
        soundManager.releaseAll()
    }
}