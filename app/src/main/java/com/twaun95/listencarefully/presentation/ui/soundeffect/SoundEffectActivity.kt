package com.twaun95.listencarefully.presentation.ui.soundeffect

import com.twaun95.listencarefully.R
import com.twaun95.listencarefully.base.BaseActivity
import com.twaun95.listencarefully.databinding.ActivitySoundEffectBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SoundEffectActivity : BaseActivity<ActivitySoundEffectBinding, SoundEffectActivityViewModel>(R.layout.activity_sound_effect){
    override val viewModel: SoundEffectActivityViewModel by viewModel()

    override fun initView() {
        super.initView()
    }

    override fun setObserver() {
        super.setObserver()
    }

    override fun setEvent() {
        super.setEvent()
    }
}