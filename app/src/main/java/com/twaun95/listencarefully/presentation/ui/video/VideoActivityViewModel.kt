package com.twaun95.listencarefully.presentation.ui.video

import com.twaun95.listencarefully.base.BaseViewModel
import com.twaun95.listencarefully.manager.sound.singleton.SoundManager

class VideoActivityViewModel(
    private val soundManager: SoundManager
) : BaseViewModel() {

    var video_id = 0

    init {
        initialize()
    }

    fun initialize() {
        soundManager.load("![CDATA[https://www.youtube.com/api/manifest/dash/id/bf5bb2419360daf1/source/youtube?as=fmp4_audio_clear,fmp4_sd_hd_clear&sparams=ip,ipbits,expire,source,id,as&ip=0.0.0.0&ipbits=0&expire=19000000000&signature=51AF5F39AB0CEC3E5497CD9C900EBFEAECCCB5C7.8506521BFC350652163895D4C26DEE124209AA9E&key=ik0]]") {
            video_id = it
        }
    }

    fun release() {

    }
}