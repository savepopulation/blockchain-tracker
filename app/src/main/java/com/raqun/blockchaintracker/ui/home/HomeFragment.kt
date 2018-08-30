package com.raqun.blockchaintracker.ui.home

import android.arch.lifecycle.Observer
import android.databinding.BindingAdapter
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import com.raqun.blockchaintracker.R
import com.raqun.blockchaintracker.databinding.FragmentHomeBinding
import com.raqun.blockchaintracker.extensions.alert
import com.raqun.blockchaintracker.extensions.observeApi
import com.raqun.blockchaintracker.ui.BinderFragment
import kotlinx.android.synthetic.main.fragment_home.*
import com.github.mikephil.charting.data.LineData
import android.support.v4.content.ContextCompat
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.LineDataSet
import android.text.format.DateUtils
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.raqun.blockchaintracker.Constants
import com.raqun.blockchaintracker.model.MarketVal
import com.raqun.blockchaintracker.util.ChartValueDateFormatter
import com.raqun.blockchaintracker.util.NonFloatValueFormatter

/**
 * Created by tyln on 29.08.2018.
 */
class HomeFragment : BinderFragment<FragmentHomeBinding, HomeViewModel>(), HomeView {

    override fun getModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun getLayoutRes(): Int = R.layout.fragment_home

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        spinnerWeek.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                // no-op
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                viewModel.setWeekPeriod(p2 + 1)
            }
        }

        viewModel.marketLiveData().observeApi(this, {
            binding.marketValuesDataBean = it
        })
    }

    override fun initView() {
        binding.view = this
    }

    companion object {
        private const val MAX_CHAR_Y_AXIS_ADDITION = 1

        fun newInstance() = HomeFragment()

        @JvmStatic
        @BindingAdapter(value = ["marketValues", "chartLabel"], requireAll = false)
        fun bindDefaultLineChart(chartView: LineChart,
                                 marketValues: List<MarketVal>?,
                                 label: String?) {
            if (marketValues == null) {
                return
            }

            val yValues = ArrayList<Entry>()
            var maxCount: Long = 0
            for (marketValue in marketValues) {
                val entry = Entry(marketValue.dateTime.toFloat(),
                        marketValue.transactionNumber.toFloat())
                yValues.add(entry)
                if (marketValue.transactionNumber > maxCount) {
                    maxCount = marketValue.transactionNumber.toLong()
                }
            }

            val xAxis = chartView.xAxis
            val xAxisFormatter = ChartValueDateFormatter(Constants.DEFAULT_DATE_FORMAT)
            xAxis.valueFormatter = xAxisFormatter

            chartView.axisLeft.apply {
                axisMaximum = (maxCount + MAX_CHAR_Y_AXIS_ADDITION).toFloat()
                valueFormatter = NonFloatValueFormatter()
            }

            val dataSet = LineDataSet(yValues, label).apply {
                axisDependency = YAxis.AxisDependency.LEFT
                color = ContextCompat.getColor(chartView.context, R.color.colorAccent)
                formLineWidth = 2f
                setDrawIcons(true)
                setDrawValues(false)
            }

            val lineData = LineData(dataSet)
            chartView.data = lineData
        }
    }
}