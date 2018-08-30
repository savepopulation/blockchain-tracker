package com.raqun.blockchaintracker.ui

import android.app.Application
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.MenuRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.view.*
import com.raqun.blockchaintracker.Constants
import com.raqun.blockchaintracker.R
import com.raqun.blockchaintracker.data.Error
import com.raqun.blockchaintracker.extensions.alert
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector

/**
 * Created by tyln on 29.08.2018.
 */
abstract class BaseFragment : Fragment(), BaseView{

    protected var navigationController: NavigationController? = null

    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        if (activity is HasSupportFragmentInjector) {
            AndroidSupportInjection.inject(this)
        }
        super.onCreate(savedInstanceState)
        navigationController = NavigationController(activity!!)
        if (getMenuRes() != Constants.NO_RES) {
            setHasOptionsMenu(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        if (getMenuRes() != Constants.NO_RES) {
            inflater?.inflate(getMenuRes(), menu)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutRes(), null, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (getTitleRes() != Constants.NO_RES) {
            setActivityTitle(getString(getTitleRes()))
        }
    }

    override fun onDestroyView() {
        navigationController = null
        super.onDestroyView()
    }

    override fun onError(e: Error?) {
        activity!!.alert(e?.message)
    }

    protected fun setActivityTitle(title: String) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).setScreenTitle(title)
        }
    }

    @MenuRes
    protected open fun getMenuRes(): Int = Constants.NO_RES

    @StringRes
    protected open fun getTitleRes(): Int = R.string.app_name

    fun getApplication(): Application = activity!!.application

    fun getApplicationContext(): Context = getApplication().applicationContext
}