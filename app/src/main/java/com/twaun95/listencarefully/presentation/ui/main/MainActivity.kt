package com.twaun95.listencarefully.presentation.ui.main

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import com.twaun95.listencarefully.R
import com.twaun95.listencarefully.base.BaseActivity
import com.twaun95.listencarefully.databinding.ActivityMainBinding
import com.twaun95.listencarefully.presentation.ui.record.RecordActivity
import com.twaun95.listencarefully.presentation.ui.record.RecordHandler
import com.twaun95.listencarefully.presentation.ui.sound.SoundActivity
import com.twaun95.listencarefully.presentation.ui.soundeffect.SoundEffectActivity
import com.twaun95.listencarefully.presentation.ui.video.VideoActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>(R.layout.activity_main) {
    override val viewModel: MainActivityViewModel by viewModel()

    private val recordHandler by inject<RecordHandler>()

    override fun onStart() {
        super.onStart()
        recordHandler.requestPermission(this)
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            RecordHandler.REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    recordHandler.initialize()
                } else {
                    Toast.makeText(this, "녹음 권한을 허용해주세요.", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                        data = Uri.parse("package:$packageName")
                    })
                }
                return
            }
        }
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