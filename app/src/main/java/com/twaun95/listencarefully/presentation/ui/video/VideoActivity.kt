package com.twaun95.listencarefully.presentation.ui.video

import com.twaun95.listencarefully.R
import com.twaun95.listencarefully.base.BaseActivity
import com.twaun95.listencarefully.databinding.ActivityVideoBinding
import com.twaun95.listencarefully.manager.sound.singleton.SoundManager
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class VideoActivity : BaseActivity<ActivityVideoBinding, VideoActivityViewModel>(R.layout.activity_video){
    override val viewModel: VideoActivityViewModel by viewModel()

    private val soundManager by inject<SoundManager>()


    override fun setObserver() {
        super.setObserver()

        viewModel.video_id.observe(this) {
            binding.videoPlayer.player = soundManager.getSoundPlayer(it)?.getPlayer()
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.initialize()
    }

    override fun onStop() {
        super.onStop()
        soundManager.releaseAll()
    }

}