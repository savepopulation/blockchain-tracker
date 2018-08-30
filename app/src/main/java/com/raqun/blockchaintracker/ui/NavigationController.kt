package com.raqun.blockchaintracker.ui

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.raqun.blockchaintracker.R
import com.raqun.blockchaintracker.extensions.transact
import com.raqun.blockchaintracker.ui.home.HomeFragment

/**
 * Created by tyln on 29.08.2018.
 */
class NavigationController(private val activity: FragmentActivity,
                           @IdRes private val containerId: Int = R.id.framelayout_main) {

    enum class NavigationType {
        BACK, ROOT
    }

    fun showHome() {
        navigate(HomeFragment.newInstance(), false)
    }

    fun close() {
        activity.finish()
    }

    private fun navigate(fragment: Fragment, isStackable: Boolean) {
        activity.supportFragmentManager.transact {
            if (isStackable) {
                addToBackStack(null)
            }
            replace(containerId, fragment)
        }
    }
}