package com.raqun.blockchaintracker.util

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter


/**
 * Created by tyln on 29.08.2018.
 */
class NonFloatValueFormatter : IndexAxisValueFormatter() {

    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
        return Math.round(value).toString()
    }
}