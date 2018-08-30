package com.raqun.blockchaintracker.api.response

import com.google.gson.annotations.SerializedName

/**
 * Created by tyln on 29.08.2018.
 */

/* Reference: https://www.blockchain.com/api/charts_api

  "status": "ok",
  "name": "Confirmed Transactions Per Day",
  "unit": "Transactions",
  "period": "day",
  "description": "The number of daily confirmed Bitcoin transactions.",
  "values":
 */

data class DefaultResponse<T>(@SerializedName("status") val status: String? = null,
                              @SerializedName("name") val name: String? = null,
                              @SerializedName("unit") val unit: String? = null,
                              @SerializedName("period") val period: String? = null,
                              @SerializedName("description") val desc: String? = null,
                              @SerializedName("values") val data: T)

