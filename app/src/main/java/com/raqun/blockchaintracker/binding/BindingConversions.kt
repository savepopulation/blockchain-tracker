package com.raqun.blockchaintracker.binding

import android.databinding.BindingConversion
import android.view.View
import com.raqun.blockchaintracker.data.DataBean
import com.raqun.blockchaintracker.data.DataState

/**
 * Created by tyln on 29.08.2018.
 */
object BindingConversions {
    @JvmStatic
    @BindingConversion
    fun <T> bindBeanToProgress(bean: DataBean<T>?): Int =
            if (bean?.getState() == DataState.FETCHING && bean.getData() == null) View.VISIBLE else View.GONE

    @JvmStatic
    @BindingConversion
    fun bindBooleanToVisiblity(isVisible: Boolean): Int = if (isVisible) View.VISIBLE else View.GONE

    @JvmStatic
    @BindingConversion
    fun bindBeanToEnableState(bean: DataBean<Any>?): Boolean = !(bean != null && bean.getState() != DataState.FETCHING)
}