package com.raqun.blockchaintracker

import android.app.Activity
import android.app.Application
import com.raqun.blockchaintracker.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by tyln on 29.08.2018.
 */
class TrackerApp : Application(), HasActivityInjector {
    @Inject
    lateinit var dispachingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        with(DaggerAppComponent.builder()) {
            application(this@TrackerApp)
            build()
        }.also {
            it.inject(this)
        }
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispachingAndroidInjector
}