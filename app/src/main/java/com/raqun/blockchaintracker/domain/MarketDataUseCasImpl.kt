package com.raqun.blockchaintracker.domain

import com.raqun.blockchaintracker.data.source.MarketDataSource
import com.raqun.blockchaintracker.data.source.MarketRepository
import com.raqun.blockchaintracker.model.MarketVal
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by tyln on 29.08.2018.
 */
class MarketDataUseCasImpl @Inject constructor(@Named("marketRepository") private val marketRepository: MarketDataSource)
    : MarketDataUseCase {

    override fun fetchWeeklyMarketTransactionData(week: Int): Observable<MarketVal> {

        val weeksQuery = with(StringBuilder()) {
            append(week)
            append(WEEK_QUERY_PARAM_SUFFIX)
            toString()
        }

        return marketRepository.getWeeklyBlockchainTransactionData(weeksQuery)
                .toObservable()
                .map {
                    it.data.toMutableList()
                }
                .flatMap {
                    Observable.fromIterable(it)
                }
                .filter {
                    /**
                     * A simple business logic do demonstrate business impl in a UseCase
                     * Just show transaction numbers bigger than zero.
                     */
                    it.transactionNumber > 0
                }
    }

    companion object {
        private const val WEEK_QUERY_PARAM_SUFFIX = "weeks"
    }
}