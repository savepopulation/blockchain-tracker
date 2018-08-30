package com.raqun.blockchaintracker.util

import android.arch.lifecycle.MediatorLiveData
import io.reactivex.internal.util.NotificationLite.getValue

/**
 * Created by tyln on 29.08.2018.
 */
class StableLiveData<T> : MediatorLiveData<T>() {

    override fun setValue(value: T) {
        if (isValueSame(value)) {
            return
        }

        super.setValue(value)
    }

    override fun postValue(value: T) {
        if (isValueSame(value)) {
            return
        }

        super.postValue(value)
    }

    fun forceSetValue(value: T) {
        super.setValue(value)
    }

    fun forcePostValue(value: T) {
        super.postValue(value)
    }

    private fun isValueSame(value: T?): Boolean {
        val currentValue = getValue()
        return currentValue === value || currentValue != null && currentValue == value
    }
}