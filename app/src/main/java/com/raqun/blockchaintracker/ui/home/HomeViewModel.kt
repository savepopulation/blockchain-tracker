package com.raqun.blockchaintracker.ui.home

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.VisibleForTesting
import com.raqun.blockchaintracker.data.DataBean
import com.raqun.blockchaintracker.domain.MarketDataUseCase
import com.raqun.blockchaintracker.extensions.createError
import com.raqun.blockchaintracker.extensions.workOnBackground
import com.raqun.blockchaintracker.model.MarketVal
import com.raqun.blockchaintracker.model.UiDataBean
import com.raqun.blockchaintracker.util.StableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by tyln on 29.08.2018.
 */
open class HomeViewModel @Inject constructor(private val marketUseCase: MarketDataUseCase) : ViewModel() {

    private val marketLiveData = MediatorLiveData<DataBean<List<MarketVal>>>()

    private val weekPeriodLiveData = StableLiveData<Int>()

    private val marketData = ArrayList<MarketVal>()

    init {
        marketLiveData.addSource(weekPeriodLiveData) {
            it?.let {
                fetchWeeklyMarketData(it)
            }
        }
    }

    open fun marketLiveData() = marketLiveData

    fun setWeekPeriod(period: Int) {
        weekPeriodLiveData?.setValue(period)
    }

    private fun fetchWeeklyMarketData(weeks: Int) {
        marketLiveData.value = UiDataBean.fetching(null)
        marketUseCase.fetchWeeklyMarketTransactionData(weeks)
                .workOnBackground()
                .subscribeBy(
                        onNext = {
                            marketData.add(it)
                        },
                        onError = {
                            marketLiveData.value = UiDataBean.error(marketData, it.createError())
                        },
                        onComplete = {
                            marketLiveData.value = UiDataBean.success(marketData)
                        }
                )
    }
}