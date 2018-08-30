package com.raqun.blockchaintracker.extensions

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by tyln on 29.08.2018.
 */
fun Long.toDate(targetDateFormat: String): String {
    val date = Date(this)
    return SimpleDateFormat(targetDateFormat).format(date)
}