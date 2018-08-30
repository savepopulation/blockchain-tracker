package com.raqun.blockchaintracker.domain

import com.raqun.blockchaintracker.model.MarketVal
import io.reactivex.Observable
import java.util.*

/**
 * Created by tyln on 29.08.2018.
 */
interface MarketDataUseCase {
    fun fetchWeeklyMarketTransactionData(week: Int): Observable<MarketVal>
}