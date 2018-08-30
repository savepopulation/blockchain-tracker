package com.raqun.blockchaintracker.model

import com.google.gson.annotations.SerializedName

/**
 * Created by tyln on 29.08.2018.
 */

/* Reference: https://www.blockchain.com/api/charts_api

    {
      "x": 1442534400, // Unix timestamp (2015-09-18T00:00:00+00:00)
      "y": 188330.0
    }

*/

data class MarketVal(@SerializedName("x") val dateTime: Long,
                     @SerializedName("y") val transactionNumber: Double)