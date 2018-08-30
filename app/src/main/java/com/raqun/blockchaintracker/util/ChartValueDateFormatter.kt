package com.raqun.blockchaintracker.util

import android.text.format.DateUtils
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.raqun.blockchaintracker.extensions.toDate


/**
 * Created by tyln on 29.08.2018.
 */
class ChartValueDateFormatter(private val targetDateFormat: String) : IndexAxisValueFormatter() {

    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
        return value.toLong().toDate(targetDateFormat)
    }
}