package com.twaun95.listencarefully.presentation.ui.record

import com.twaun95.listencarefully.R
import com.twaun95.listencarefully.base.BaseActivity
import com.twaun95.listencarefully.databinding.ActivityRecordBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecordActivity : BaseActivity<ActivityRecordBinding, RecordActivityViewModel>(R.layout.activity_record){
    override val viewModel: RecordActivityViewModel by viewModel()

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