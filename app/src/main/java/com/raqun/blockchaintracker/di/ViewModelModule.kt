package com.raqun.blockchaintracker.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.raqun.blockchaintracker.ui.home.HomeViewModel
import com.raqun.blockchaintracker.viewmodel.VMFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by tyln on 29.08.2018.
 */
@Module
internal abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    // Factory
    @Binds
    abstract fun bindViewModelFactory(vmFactory: VMFactory): ViewModelProvider.Factory
}