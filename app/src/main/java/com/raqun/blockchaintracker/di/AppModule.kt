package com.raqun.blockchaintracker.di

import android.app.Application
import com.raqun.blockchaintracker.TrackerApp
import com.raqun.blockchaintracker.api.TrackerServices
import com.raqun.blockchaintracker.data.source.MarketDataSource
import com.raqun.blockchaintracker.data.source.MarketRepository
import com.raqun.blockchaintracker.data.source.remote.MarketRemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by tyln on 29.08.2018.
 */
@Module(includes = [(ViewModelModule::class), (DomainModule::class)])
internal class AppModule {

    /**
     * App Scope Items
     */

    @Provides
    @Singleton
    fun provideApplicationContext(app: Application) = app.applicationContext

    /**
     * Market Items
     */

    @Provides
    @Singleton
    @Named(MarketRemoteDataSource.NAME)
    fun provideMarketRemoteDataSource(trackerServices: TrackerServices): MarketDataSource =
            MarketRemoteDataSource(trackerServices)

    @Provides
    @Singleton
    @Named(MarketRepository.NAME)
    fun provideMarketRepository(@Named(MarketRemoteDataSource.NAME) marketRemoteDataSource: MarketDataSource): MarketDataSource =
            MarketRepository(marketRemoteDataSource)
}