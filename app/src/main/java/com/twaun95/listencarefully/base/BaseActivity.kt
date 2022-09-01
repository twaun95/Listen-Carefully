package com.twaun95.listencarefully.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VB: ViewDataBinding, VM: BaseViewModel>(@LayoutRes private val layoutId: Int) : AppCompatActivity() {

    protected lateinit var binding : VB
    abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this

        initView()
        setEvent()
        setObserver()
    }

    override fun onDestroy() {
        binding.unbind()
        super.onDestroy()
    }

    open fun initView() {}
    open fun setEvent() {}
    open fun setObserver() {}
}