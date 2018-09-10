package com.raqun.blockchaintracker.data.source.remote

import com.raqun.blockchaintracker.api.TrackerServices
import com.raqun.blockchaintracker.api.response.DefaultResponse
import com.raqun.blockchaintracker.data.source.MarketDataSource
import com.raqun.blockchaintracker.model.MarketVal
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by tyln on 29.08.2018.
 */
class MarketRemoteDataSource @Inject constructor(private val trackerServices: TrackerServices) : MarketDataSource {

    override fun getWeeklyBlockchainTransactionData(weeks: String): Single<DefaultResponse<List<MarketVal>>> =
            trackerServices.getWeeklyBlockchainTransactionData(weeks)

    companion object {
        const val NAME = "marketRemoteDataSource"
    }
}