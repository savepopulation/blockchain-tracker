package com.raqun.blockchaintracker.ui

import com.raqun.blockchaintracker.data.Error

/**
 * Created by tyln on 29.08.2018.
 */

interface BaseView {
    fun onError(e: Error?)
}