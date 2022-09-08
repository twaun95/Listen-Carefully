package com.twaun95.listencarefully.presentation.ui.record

import android.util.Log
import com.twaun95.listencarefully.R
import com.twaun95.listencarefully.base.BaseActivity
import com.twaun95.listencarefully.databinding.ActivityRecordBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecordActivity : BaseActivity<ActivityRecordBinding, RecordActivityViewModel>(R.layout.activity_record){
    override val viewModel: RecordActivityViewModel by viewModel()

    override fun setObserver() {
        super.setObserver()
        viewModel.recordState.observe(this) {
            if (it==State.READY_PLAY) binding.viewCountTime.stopCountUp()
            binding.buttonRecord.update(it)
        }
    }

    override fun setEvent() {
        super.setEvent()
        binding.viewSoundVisual.onRequestCurrentAmplitude = { viewModel.getAmplitude() }
        binding.buttonRecord.setOnClickListener {
            when(viewModel.recordState.value!!) {
                State.IDLE -> {
                    viewModel.startRecord()
                    binding.viewCountTime.startCountUp()
                    binding.viewSoundVisual.startVisualizing(false)
                }
                State.ON_RECORD -> {
                    viewModel.stopRecord()
                    binding.viewSoundVisual.stopVisualizing()
                }
                State.READY_PLAY -> {
                    viewModel.startPlay()
                    binding.viewCountTime.startCountUp()
                    binding.viewSoundVisual.startVisualizing(true)
                }
                State.ON_PLAY -> {
                    viewModel.stopPlay()
                    binding.viewSoundVisual.stopVisualizing()
                }
            }
        }

        binding.buttonReset.setOnClickListener {
            viewModel.reset()
            binding.viewCountTime.clearCountTime()
            binding.viewSoundVisual.clearVisualization()
        }
    }

    override fun onStop() {
        super.onStop()
        when(viewModel.recordState.value!!) {
            State.IDLE -> {}
            State.ON_RECORD -> {
                viewModel.stopRecord()
                binding.viewSoundVisual.stopVisualizing()
            }
            State.READY_PLAY -> {}
            State.ON_PLAY -> {
                viewModel.stopPlay()
                binding.viewSoundVisual.stopVisualizing()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.release()
    }
}