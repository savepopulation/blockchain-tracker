package com.raqun.blockchaintracker.api

import com.raqun.blockchaintracker.api.response.DefaultResponse
import com.raqun.blockchaintracker.model.MarketVal
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by tyln on 29.08.2018.
 */
interface TrackerServices {
    @GET("charts/transactions-per-second?rollingAverage=8hours&format=json")
    fun getWeeklyBlockchainTransactionData(@Query("timespan") timeSpan: String): Single<DefaultResponse<List<MarketVal>>>
}