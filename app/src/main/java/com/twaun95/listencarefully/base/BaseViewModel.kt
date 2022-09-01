package com.twaun95.listencarefully.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel(){

    val error by lazy { MutableLiveData<Throwable>() }
    val isLoading by lazy { MutableLiveData(false) }
}