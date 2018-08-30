package com.raqun.blockchaintracker.ui.view

import android.content.Context
import android.databinding.adapters.TextViewBindingAdapter.setText
import android.util.AttributeSet
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.components.XAxis
import com.raqun.blockchaintracker.R


/**
 * Created by tyln on 29.08.2018.
 */
open class DefaultLineChart @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0)
    : LineChart(context, attrs, defStyle) {

    init {
        setupChart()
    }

    private fun setupChart() {

        // Init Description
        val description = Description().apply {
            isEnabled = false
        }
        setDescription(description)

        // Init GridBackground
        setGridBackgroundColor(android.R.color.white)
        setDrawGridBackground(true)

        // Init Borders
        setDrawBorders(true)
        setBorderColor(android.R.color.black)
        setBorderWidth(1f)

        // Init Other Properties
        setPinchZoom(false)
        isDoubleTapToZoomEnabled = false
        isDragEnabled = true
        setNoDataText(context.getString(R.string.info_text_no_content))
        setScaleEnabled(true)

        // Init Legend
        val legend = legend.apply {
            isEnabled = false
        }

        // Init xAxis
        val xAxis = xAxis.apply {
            isEnabled = true
            setCenterAxisLabels(false)
            gridColor = android.R.color.white
            setAvoidFirstLastClipping(false)
            setDrawLimitLinesBehindData(true)
            position = XAxis.XAxisPosition.BOTTOM
        }


        // Init leftAxis
        val leftAxis = axisLeft.apply {
            axisMinimum = 0f
            setDrawAxisLine(false)
            setDrawZeroLine(true)
            setDrawGridLines(true)
            gridColor = android.R.color.black
            axisLineColor = android.R.color.black
        }

        val rightAxis = axisRight.apply {
            isEnabled = false
        }
    }

    fun setChartTitle(title: String) {
        val description = Description().apply {
            text = title
            isEnabled = true
        }
        setDescription(description)
    }
}