package com.raqun.blockchaintracker.extensions

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import com.raqun.blockchaintracker.data.DataBean
import com.raqun.blockchaintracker.ui.BaseView
import com.raqun.blockchaintracker.data.Error

/**
 * Created by tyln on 29.08.2018.
 */

inline fun <T> LiveData<DataBean<T>>.observeApi(lifecycleOwner: LifecycleOwner,
                                                crossinline body: (DataBean<T>?) -> Unit) {
    observe(lifecycleOwner, Observer { bean: DataBean<T>? ->
        if (bean != null && bean.hasError()) {
            if (lifecycleOwner is BaseView) {
                lifecycleOwner.onError(bean.getError())
            }
        }
        body(bean)
    })
}