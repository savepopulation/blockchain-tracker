package com.raqun.blockchaintracker.di

import com.raqun.blockchaintracker.ui.home.HomeFragment
import com.raqun.blockchaintracker.ui.home.HomeViewModel
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by tyln on 29.08.2018.
 */
@Module
internal abstract class FragmentBuildersModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment
}