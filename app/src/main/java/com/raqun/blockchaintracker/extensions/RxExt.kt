package com.raqun.blockchaintracker.extensions

import com.raqun.blockchaintracker.data.Error
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by tyln on 29.08.2018.
 */
fun <T> Observable<T>.workOnBackground(): Observable<T> = this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

fun Throwable?.createError(): Error? = com.raqun.blockchaintracker.data.Error(0, this?.message)