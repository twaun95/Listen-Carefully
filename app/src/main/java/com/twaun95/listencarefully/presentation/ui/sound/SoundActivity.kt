package com.twaun95.listencarefully.presentation.ui.sound

import android.graphics.Color
import com.twaun95.listencarefully.R
import com.twaun95.listencarefully.base.BaseActivity
import com.twaun95.listencarefully.databinding.ActivitySoundBinding
import com.twaun95.listencarefully.manager.sound.factory.ExoPlayerFactory
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SoundActivity : BaseActivity<ActivitySoundBinding, SoundActivityViewModel>(R.layout.activity_sound) {
    override val viewModel: SoundActivityViewModel by viewModel()

    private val exoPlayerHandler1 by inject<ExoPlayerFactory>()
    private val exoPlayerHandler2 by inject<ExoPlayerFactory>()
    private val exoPlayerHandler3 by inject<ExoPlayerFactory>()

    override fun initView() {
        super.initView()

        binding.player1.player = exoPlayerHandler1.apply { setData(viewModel.playList[0]) }.player
        binding.player2.player = exoPlayerHandler2.apply { setData(viewModel.playList[1]) }.player
        binding.player3.player = exoPlayerHandler3.apply {
            setData(viewModel.playList[2])
        }.player
    }

    override fun setObserver() {
        super.setObserver()
    }

    override fun setEvent() {
        super.setEvent()
        binding.player3.setBackgroundColor(Color.GREEN)
    }

    override fun onStop() {
        super.onStop()
    }
}
