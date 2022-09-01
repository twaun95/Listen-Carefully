package com.twaun95.listencarefully.presentation.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.twaun95.listencarefully.R
import com.twaun95.listencarefully.base.BaseActivity
import com.twaun95.listencarefully.databinding.ActivityMainBinding
import com.twaun95.listencarefully.presentation.ui.record.RecordActivity
import com.twaun95.listencarefully.presentation.ui.sound.SoundActivity
import com.twaun95.listencarefully.presentation.ui.soundeffect.SoundEffectActivity
import com.twaun95.listencarefully.presentation.ui.video.VideoActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>(R.layout.activity_main) {
    override val viewModel: MainActivityViewModel by viewModel()

    override fun initView() {
        super.initView()
    }

    override fun setObserver() {
        super.setObserver()
    }

    override fun setEvent() {
        super.setEvent()
        binding.buttonSoundEffect.setOnClickListener {
            val intent = Intent(this, SoundEffectActivity::class.java)
            startActivity(intent)
        }
        binding.buttonSound.setOnClickListener {
            val intent = Intent(this, SoundActivity::class.java)
            startActivity(intent)
        }
        binding.buttonVideo.setOnClickListener {
            val intent = Intent(this, VideoActivity::class.java)
            startActivity(intent)
        }
        binding.buttonRecord.setOnClickListener {
            val intent = Intent(this, RecordActivity::class.java)
            startActivity(intent)
        }
    }
}