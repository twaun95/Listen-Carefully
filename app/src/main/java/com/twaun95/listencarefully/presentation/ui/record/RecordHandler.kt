package com.twaun95.listencarefully.presentation.ui.record

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.FileDescriptor

class RecordHandler(private val applicationContext: Context) {

    companion object {
        private const val BIT_DEPTH = 16
        private const val SAMPLE_RATE = 44100
        private const val BIT_RATE = BIT_DEPTH * SAMPLE_RATE
        const val REQUEST_CODE = 200
        const val recordFileName = "record_file.acc"
    }

    private var recordTimerThread: Thread? = null

    private lateinit var mediaRecorder: MediaRecorder

    var isInitialized = false
    var requestCount = 0
    private var permissions: Array<String> = arrayOf(Manifest.permission.RECORD_AUDIO)

    init {
//        initialize()
//        loadFile()
    }

    fun requestPermission(activity: Activity) {
        ActivityCompat.requestPermissions(activity, permissions, REQUEST_CODE)
        requestCount++
    }

    fun initialize() {
        if (isInitialized) return
        mediaRecorder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            MediaRecorder(applicationContext)
        } else {
            MediaRecorder()
        }

        isInitialized = true
        loadFile()
    }

    // 요청 결과가 녹음 허용 성공인지
    fun checkPermission(requestCode: Int, permissionGranted: Int): Boolean {
        return requestCode == REQUEST_CODE && permissionGranted == PackageManager.PERMISSION_GRANTED
    }

    // 현재 녹음 허용 상태인지
    fun checkPermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun loadFile(fileDescriptor: FileDescriptor) {
        mediaRecorder.reset()
        with(mediaRecorder) {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS)
            setOutputFile(fileDescriptor)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setAudioEncodingBitRate(BIT_RATE)
            setAudioSamplingRate(SAMPLE_RATE)
        }
    }

    fun loadFile() {
        mediaRecorder.reset()
        with(mediaRecorder) {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS)
            setOutputFile(applicationContext.filesDir.absolutePath + recordFileName)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setAudioEncodingBitRate(BIT_RATE)
            setAudioSamplingRate(SAMPLE_RATE)
        }
    }

    fun startRecord() {
        try {
            recordTimerThread = Thread()
            recordTimerThread?.start()
            RecordTimerThread(recordTimerThread).start()

            mediaRecorder.run {
                this.prepare()
                this.start()
            }
        } catch (e: Exception) {
//            Logger.debug("recordError $e")
        }
    }

    fun stopRecord() {
        try {
            mediaRecorder.let {
                it.stop()
            }
            RecordTimerThread(recordTimerThread).stop()
            recordTimerThread = null

        } catch (e: Exception) {
//            Logger.debug("recordStopError $e")
        }

    }

    fun getDecibelValue(): Int {
        return try {
            mediaRecorder.maxAmplitude
        } catch (e: Exception) {
            0
        }
    }

    private fun reset() = mediaRecorder.reset()

    fun release() {
        mediaRecorder.reset()
        mediaRecorder.release()
        isInitialized = false
    }
}

class RecordTimerThread(target: Runnable?) : Thread(target) {

    private var isRunning = false

    override fun run() {
        super.run()
        isRunning = true
        while (isRunning) {
            sleep(1000)
        }
        join()
    }

//    fun stop(){
//        isRunning = false
//    }
}