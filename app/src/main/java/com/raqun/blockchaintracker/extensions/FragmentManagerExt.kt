package com.raqun.blockchaintracker.extensions

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

/**
 * Created by tyln on 29.08.2018.
 */
inline fun FragmentManager.transact(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}