package com.twaun95.listencarefully.manager.sound.singleton

import android.content.Context
import android.net.Uri
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import java.io.File

class SoundPlayer(context: Context) {

    private lateinit var exoPlayer: ExoPlayer

    private var onPrepareListener : (() -> Unit)? = null
    private var onSoundEndListener : (() -> Unit)? = null
    private var playerListener: SoundPlayerListener? = null
    private var isSeekTo = false

    init {
        initialize(context)
    }

    private fun initialize(context: Context) {
        exoPlayer = ExoPlayer.Builder(context).setPauseAtEndOfMediaItems(true).build().apply {
            playerListener = SoundPlayerListener(isSeekTo, {
                onPrepareListener?.invoke()
            }, {
                onSoundEndListener?.invoke()
            }).also {
                addListener(it)
            }
        }
    }

    private fun insert(mediaItems : List<MediaItem>) {
        exoPlayer.setMediaItems(mediaItems)
        exoPlayer.prepare()
    }

    fun setDataFile(fileName : String, prepareListener: (()-> Unit)?) {
        this.onPrepareListener = prepareListener
        insert(listOf(MediaItem.fromUri(Uri.fromFile(File(fileName)))))
    }

    fun setData(urls : List<String>, prepareListener: (() -> Unit)?) {
        this.onPrepareListener = prepareListener
        insert(urls.map { MediaItem.fromUri(Uri.parse(it)) })
    }

    fun playPlayer(index : Int, soundEndListener: (() -> Unit)?) {
        this.onSoundEndListener = soundEndListener
        exoPlayer.pause()
        isSeekTo = true
        playerListener?.updateSeekTo(isSeekTo)
        exoPlayer.seekTo(index, 0L)
        exoPlayer.playWhenReady = false
        exoPlayer.play()
    }

    fun getPlayer(): ExoPlayer = exoPlayer


    fun pausePlayer() = exoPlayer.pause()

    fun stopAndReleasePlayer() {
        onPrepareListener = null
        onSoundEndListener = null

        exoPlayer.stop()
        exoPlayer.release()
        playerListener?.let {
            it.removeListener()
            exoPlayer.removeListener(it)
            playerListener = null
        }

    }
}
