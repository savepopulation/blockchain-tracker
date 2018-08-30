package com.raqun.blockchaintracker.ui

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.MenuRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.raqun.blockchaintracker.Constants
import com.raqun.blockchaintracker.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Created by tyln on 29.08.2018.
 */
abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @LayoutRes
    abstract fun getLayoutRes(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
        setScreenTitle(getString(getScreenTitle()))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (getMenuRes() != Constants.NO_RES) {
            menuInflater.inflate(getMenuRes(), menu)
            return true
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    @MenuRes
    protected open fun getMenuRes(): Int = Constants.NO_RES


    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    @StringRes
    protected open fun getScreenTitle() = R.string.app_name

    fun setScreenTitle(title: String?) {
        title?.let {
            supportActionBar?.title = title
        }
    }
}