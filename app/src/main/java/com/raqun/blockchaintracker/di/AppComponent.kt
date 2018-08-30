package com.raqun.blockchaintracker.di

import com.raqun.blockchaintracker.TrackerApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by tyln on 29.08.2018.
 */
@Singleton
@Component(modules = [(AndroidInjectionModule::class),
    (AppModule::class),
    (ApiModule::class),
    (ActivityModule::class)])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: TrackerApp): Builder

        fun build(): AppComponent
    }

    fun inject(application: TrackerApp)
}