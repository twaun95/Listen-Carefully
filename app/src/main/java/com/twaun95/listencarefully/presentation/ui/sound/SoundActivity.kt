package com.twaun95.listencarefully.presentation.ui.sound

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.twaun95.listencarefully.R
import com.twaun95.listencarefully.base.BaseActivity
import com.twaun95.listencarefully.databinding.ActivitySoundBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SoundActivity : BaseActivity<ActivitySoundBinding, SoundActivityViewModel>(R.layout.activity_sound) {
    override val viewModel: SoundActivityViewModel by viewModel()

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
