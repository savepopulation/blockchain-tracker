package com.raqun.blockchaintracker

import com.raqun.blockchaintracker.model.MarketVal

class TestDataProvider private constructor() {
    companion object {
        private const val timeStampStartVal = 1442534400
        private const val transactionStartVal = 188330.0

        private const val timeSampInterval = 100
        private const val transactionInterval = 1000

        fun providMarketValues(count: Int = 10): List<MarketVal> {
            val values = ArrayList<MarketVal>()
            for (i in 0..count) {
                values.add(MarketVal(timeStampStartVal + (i * timeSampInterval).toLong(),
                        transactionStartVal + (i * transactionInterval).toDouble()))
            }
            return values
        }

        fun provideMarketValuesWithNegativeTransactionNumbers(marketValues: List<MarketVal>): List<MarketVal> {
            val values = ArrayList<MarketVal>()
            values.addAll(marketValues)
            values.add(MarketVal(timeStampStartVal.toLong(), -100.0))
            values.add(MarketVal(timeStampStartVal.toLong(), -110.0))
            values.add(MarketVal(timeStampStartVal.toLong(), -120.0))
            return values
        }
    }
}