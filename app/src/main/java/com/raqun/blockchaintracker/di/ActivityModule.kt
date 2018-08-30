package com.raqun.blockchaintracker.di

import com.raqun.blockchaintracker.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by tyln on 29.08.2018.
 */
@Module
internal abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [(FragmentBuildersModule::class)])
    @ActivityScope
    abstract fun provideMainActivityContributor(): MainActivity
}