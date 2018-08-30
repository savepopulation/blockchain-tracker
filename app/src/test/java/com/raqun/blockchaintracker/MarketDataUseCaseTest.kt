package com.raqun.blockchaintracker

import com.raqun.blockchaintracker.api.response.DefaultResponse
import com.raqun.blockchaintracker.data.source.MarketRepository
import com.raqun.blockchaintracker.domain.MarketDataUseCasImpl
import com.raqun.blockchaintracker.model.MarketVal
import com.raqun.blockchaintracker.util.UnitTestsDataProvider
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito

class MarketDataUseCaseTest {

    private var marketRepository = Mockito.mock(MarketRepository::class.java)!!

    private val marketDataUseCase by lazy {
        MarketDataUseCasImpl(marketRepository)
    }

    /**
     * Test for api success response
     */
    @Test
    fun testMarketDataUseCase_getWeeklyBlockchainTransactionData_Success() {
        val week = 4
        val maxTransactionItemCount = 10
        val successResponse = Single.just(DefaultResponse(data = UnitTestsDataProvider.providMarketValues(maxTransactionItemCount)))

        Mockito.`when`(marketRepository.getWeeklyBlockchainTransactionData(anyString()))
                .thenReturn(successResponse)

        marketDataUseCase.fetchWeeklyMarketTransactionData(week)
                .test()
                .assertSubscribed()
                .assertComplete()
    }

    /**
     * Tests for api error response
     */
    @Test
    fun testMarketDataUseCase_getWeeklyBlockchainTransactionData_Error() {
        val week = 4
        val errorResponse = Throwable("Error..")

        Mockito.`when`(marketRepository.getWeeklyBlockchainTransactionData(anyString()))
                .thenReturn(Single.error(errorResponse))

        marketDataUseCase.fetchWeeklyMarketTransactionData(week)
                .test()
                .assertSubscribed()
                .assertError(errorResponse)
    }

    /**
     * Test for MarketUseCase business logic which does not accept negative values for week.
     */
    @Test
    fun testMarketDataUseCase_getWeeklyBlockchainTransactionData_NegativeWeek() {
        val week = -1
        val error: Observable<MarketVal> = Observable.error(IllegalArgumentException("Week cannot be negative number!"))

        assert(marketDataUseCase.fetchWeeklyMarketTransactionData(week)
                .test() == error)
    }

    /**
     * Test for MarketUseCase business logic which filters possible negative transaction responses.
     */
    @Test
    fun testMarketDataUseCase_getWeeklyBlockchainTransactionData_NegativeTransactionNumbers() {
        val week = 4
        val maxTransactionItemCount = 10
        val suitableValues = UnitTestsDataProvider.providMarketValues(maxTransactionItemCount)
        val successResponse = Single.just(DefaultResponse(data = UnitTestsDataProvider.provideMarketValuesWithNegativeTransactionNumbers(suitableValues)))

        Mockito.`when`(marketRepository.getWeeklyBlockchainTransactionData(anyString()))
                .thenReturn(successResponse)

        marketDataUseCase.fetchWeeklyMarketTransactionData(week)
                .test()
                .assertSubscribed()
                .assertComplete()
                .assertValueSet(suitableValues)
    }
}