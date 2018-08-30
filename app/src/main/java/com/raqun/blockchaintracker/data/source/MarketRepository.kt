package com.raqun.blockchaintracker.data.source

import com.raqun.blockchaintracker.api.response.DefaultResponse
import com.raqun.blockchaintracker.data.source.remote.MarketRemoteDataSource
import com.raqun.blockchaintracker.model.MarketVal
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by tyln on 29.08.2018.
 */
/**
 * Created by tyln on 29.08.2018.
 */
class MarketRepository @Inject constructor(@Named("marketRemoteDataSource") private val marketRemoteDataSource: MarketDataSource)
    : MarketDataSource {

    override fun getWeeklyBlockchainTransactionData(weeks: String): Single<DefaultResponse<List<MarketVal>>> =
            marketRemoteDataSource.getWeeklyBlockchainTransactionData(weeks)
}