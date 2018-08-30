package com.raqun.blockchaintracker

import com.raqun.blockchaintracker.api.response.DefaultResponse
import com.raqun.blockchaintracker.data.source.MarketRepository
import com.raqun.blockchaintracker.domain.MarketDataUseCasImpl
import com.raqun.blockchaintracker.domain.MarketDataUseCase
import com.raqun.blockchaintracker.model.MarketVal
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxkotlin.toSingle
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import kotlin.math.max

class MarketDataUseCaseTest {

    private var marketRepository = Mockito.mock(MarketRepository::class.java)!!

    private val marketDataUseCase by lazy {
        MarketDataUseCasImpl(marketRepository)
    }

    @Test
    fun testMarketDataUseCase_getWeeklyBlockchainTransactionData_Success() {
        val week = 4
        val maxTransactionItemCount = 10

        Mockito.`when`(marketRepository.getWeeklyBlockchainTransactionData(anyString()))
                .thenReturn(Single.just(DefaultResponse(data = TestDataProvider.providMarketValues(maxTransactionItemCount))))


        marketDataUseCase.fetchWeeklyMarketTransactionData(week)
                .test()
                .assertSubscribed()
                .assertComplete()
    }

    @Test
    fun testMarketDataUseCase_getWeeklyBlockchainTransactionData_Error() {
        val week = 4
        val response = Throwable("Error..")

        Mockito.`when`(marketRepository.getWeeklyBlockchainTransactionData(anyString()))
                .thenReturn(Single.error(response))

        marketDataUseCase.fetchWeeklyMarketTransactionData(week)
                .test()
                .assertSubscribed()
                .assertError(response)
    }

    @Test
    fun testMarketDataUseCase_getWeeklyBlockchainTransactionData_NegativeWeek() {
        val week = -1
        val error: Observable<MarketVal> = Observable.error(IllegalArgumentException("Week cannot be negative number!"))

        assert(marketDataUseCase.fetchWeeklyMarketTransactionData(week)
                .test() == error)
    }

    @Test
    fun testMarketDataUseCase_getWeeklyBlockchainTransactionData_NegativeTransactionNumbers() {
        val week = 4
        val maxTransactionItemCount = 10
        val suitableValues = TestDataProvider.providMarketValues(maxTransactionItemCount)

        Mockito.`when`(marketRepository.getWeeklyBlockchainTransactionData(anyString()))
                .thenReturn(Single.just(DefaultResponse(data = TestDataProvider.provideMarketValuesWithNegativeTransactionNumbers(suitableValues))))

        marketDataUseCase.fetchWeeklyMarketTransactionData(week)
                .test()
                .assertSubscribed()
                .assertComplete()
                .assertValueSet(suitableValues)
    }
}