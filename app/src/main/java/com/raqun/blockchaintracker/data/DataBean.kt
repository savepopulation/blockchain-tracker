package com.raqun.blockchaintracker.data

import com.raqun.blockchaintracker.data.Error

/**
 * Created by tyln on 29.08.2018.
 */
interface DataBean<out T> {
    fun getData(): T?

    fun getState(): DataState

    fun hasError(): Boolean

    fun getError(): Error?
}