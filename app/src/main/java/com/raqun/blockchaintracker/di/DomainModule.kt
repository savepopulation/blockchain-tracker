package com.raqun.blockchaintracker.di

import com.raqun.blockchaintracker.data.source.MarketDataSource
import com.raqun.blockchaintracker.data.source.MarketRepository
import com.raqun.blockchaintracker.domain.MarketDataUseCasImpl
import com.raqun.blockchaintracker.domain.MarketDataUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Created by tyln on 29.08.2018.
 */
@Module
internal class DomainModule {
    @Provides
    fun provideMarketUseCase(@Named("marketRepository") marketRepository: MarketDataSource): MarketDataUseCase =
            MarketDataUseCasImpl(marketRepository)
}