package com.twaun95.listencarefully.presentation.ui.record

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import com.twaun95.listencarefully.R
import com.twaun95.listencarefully.base.BaseActivity
import com.twaun95.listencarefully.databinding.ActivityRecordBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecordActivity : BaseActivity<ActivityRecordBinding, RecordActivityViewModel>(R.layout.activity_record){
    override val viewModel: RecordActivityViewModel by viewModel()

    override fun initView() {
        super.initView()
    }

    override fun setObserver() {
        super.setObserver()

        viewModel.recordState.observe(this) { binding.buttonRecord.update(it) }
    }

    override fun setEvent() {
        super.setEvent()

        binding.buttonRecord.setOnClickListener {
            when(viewModel.recordState.value!!) {
                State.IDLE -> {
                    viewModel.startRecord()
                }
                State.ON_RECORD -> {
                    viewModel.stopRecord()
                }
                State.READY_PLAY -> {
                    viewModel.startPlay()
                }
                State.ON_PLAY -> {
                    viewModel.stopPlay()
                }
            }
        }

        binding.buttonReset.setOnClickListener {
            viewModel.reset()
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.release()
    }
}