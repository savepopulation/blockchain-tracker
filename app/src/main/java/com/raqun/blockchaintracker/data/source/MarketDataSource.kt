package com.raqun.blockchaintracker.data.source

import com.raqun.blockchaintracker.api.response.DefaultResponse
import com.raqun.blockchaintracker.model.MarketVal
import io.reactivex.Single

/**
 * Created by tyln on 29.08.2018.
 */
interface MarketDataSource {
    fun getWeeklyBlockchainTransactionData(weeks: String): Single<DefaultResponse<List<MarketVal>>>
}