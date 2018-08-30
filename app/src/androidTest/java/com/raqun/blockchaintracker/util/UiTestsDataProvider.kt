package com.raqun.blockchaintracker.util

import com.raqun.blockchaintracker.model.MarketVal

class UiTestsDataProvider {
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
    }
}