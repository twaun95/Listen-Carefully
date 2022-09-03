package com.twaun95.listencarefully.manager.sound.singleton

import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player

class SoundPlayerListener(
    var isSeekTo: Boolean,
    private var onPrepareListener: (()->Unit)? = null,
    private var onSoundEndListener: (()->Unit)? = null,
) : Player.Listener {

    override fun onPlaybackStateChanged(playbackState: Int) {
        when (playbackState) {
            ExoPlayer.STATE_READY -> {
                if(isSeekTo) {
                    isSeekTo = false
                } else {
                    onPrepareListener?.invoke()
                }
            }
            ExoPlayer.STATE_ENDED -> {
                onSoundEndListener?.invoke()
            }

            else -> { }
        }
    }

    fun updateSeekTo(isSeekTo: Boolean){
        this.isSeekTo = isSeekTo
    }

    fun removeListener() {
        onPrepareListener = null
        onSoundEndListener = null
    }
}