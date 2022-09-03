package com.twaun95.listencarefully.manager.sound.factory

import android.content.Context
import android.net.Uri
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import java.io.File


class ExoPlayerFactory(context : Context) {

    var player : ExoPlayer = ExoPlayer.Builder(context).setPauseAtEndOfMediaItems(true).build().apply {
        addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                when (playbackState) {
                    ExoPlayer.STATE_READY -> {
                        if(isSeekTo) {
                            isSeekTo = false
                        } else {
                            onPrepareListener?.invoke()
                            onFilePrepareListener?.invoke()
                        }
                    }
                    ExoPlayer.STATE_ENDED -> {
                        onSoundEndListener?.invoke()
                        onSoundFinishListener?.invoke()
                    }

                    else -> { }
                }
            }
        })
    }

    var onSoundEndListener : (()->Unit)? = null
    var onPrepareListener : (()->Unit)? = null
    var onFilePrepareListener : (() -> Unit)? = null
    var onSoundFinishListener : (() -> Unit)? = null


    var isSeekTo = false
    var isInitialized = false


    private fun insert(mediaItems : List<MediaItem>) {
        player.setMediaItems(mediaItems)
        player.prepare()
    }

    fun setDataFile(fileName : String) = insert(listOf(MediaItem.fromUri(Uri.fromFile(File(fileName)))))

    fun setData(urls : List<String>) = insert(urls.map { MediaItem.fromUri(Uri.parse(it)) })

    fun setData(uri : String, onSoundEndListener : (()->Unit)? = null, onPrepareListener : (()->Unit)? = null) {
        this.onSoundEndListener = onSoundEndListener
        this.onPrepareListener = onPrepareListener
        setData(listOf(uri))
    }


    fun playPlayer() {
        player.pause()
        isSeekTo = true
        player.seekTo(0L)
        player.playWhenReady = false
        player.play()
    }

    fun playPlayer(index : Int) {
        player.pause()
        isSeekTo = true
        player.seekTo(index, 0L)
        player.playWhenReady = false
        player.play()
    }

    fun pausePlayer() = player.pause()

    fun stopAndReleasePlayer() {
        player.stop()
        player.release()
    }
}