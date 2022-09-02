package com.twaun95.listencarefully.presentation.ui.soundeffect

import com.twaun95.listencarefully.R
import com.twaun95.listencarefully.base.BaseActivity
import com.twaun95.listencarefully.databinding.ActivitySoundEffectBinding
import com.twaun95.listencarefully.manager.sound_effect.Effect
import com.twaun95.listencarefully.manager.sound_effect.SoundEffectManager
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SoundEffectActivity : BaseActivity<ActivitySoundEffectBinding, SoundEffectActivityViewModel>(R.layout.activity_sound_effect){
    override val viewModel: SoundEffectActivityViewModel by viewModel()

    override fun initView() {
        super.initView()

        binding.viewModel = this.viewModel
    }

    override fun setObserver() {
        super.setObserver()
    }

    override fun setEvent() {
        super.setEvent()

    }


    override fun onDestroy() {
        super.onDestroy()
        viewModel.release()
    }

}