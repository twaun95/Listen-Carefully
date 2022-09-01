package com.twaun95.listencarefully.presentation.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.twaun95.listencarefully.R
import com.twaun95.listencarefully.base.BaseActivity
import com.twaun95.listencarefully.databinding.ActivityMainBinding
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
    }
}