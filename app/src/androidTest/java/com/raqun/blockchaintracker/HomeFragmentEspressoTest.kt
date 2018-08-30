package com.raqun.blockchaintracker

import android.arch.lifecycle.MediatorLiveData
import android.support.test.espresso.Espresso
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.raqun.blockchaintracker.data.DataBean
import com.raqun.blockchaintracker.model.MarketVal
import com.raqun.blockchaintracker.model.UiDataBean
import com.raqun.blockchaintracker.ui.home.HomeFragment
import com.raqun.blockchaintracker.ui.home.HomeViewModel
import com.raqun.blockchaintracker.util.ViewModelUtil
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import com.raqun.blockchaintracker.data.Error
import com.raqun.blockchaintracker.util.UiTestsDataProvider
import org.hamcrest.Matchers.not
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.Espresso.onView
import org.hamcrest.Matchers.`is`


@RunWith(AndroidJUnit4::class)
class HomeFragmentEspressoTest {

    lateinit var homeFragment: HomeFragment
    lateinit var homeViewModel: HomeViewModel
    lateinit var marketDataLiveData: MediatorLiveData<DataBean<List<MarketVal>>>

    @get:Rule
    var activityTestRule: ActivityTestRule<SingleFragmentActivity> =
            ActivityTestRule(SingleFragmentActivity::class.java, true, true)

    @Before
    fun init() {
        homeFragment = HomeFragment.newInstance()
        homeViewModel = Mockito.mock(HomeViewModel::class.java)
        marketDataLiveData = MediatorLiveData()

        Mockito.`when`(homeViewModel.marketLiveData()).thenReturn(marketDataLiveData)

        homeFragment.vmFactory = ViewModelUtil.createFor(homeViewModel)
        activityTestRule.activity.setFragment(homeFragment)
    }

    /**
     * Test for checking if we show progress bar while fetching market data.
     */
    @Test
    fun ensureProgressVisiblityWhenFetchingMarketData() {
        val fetchingBean = UiDataBean.fetching(null)
        marketDataLiveData.postValue(fetchingBean)
        onView(withId(R.id.transactionDataProgress)).check(matches(isDisplayed()))
    }

    /**
     * Test for checking if we disable week spinner to avoid multiple requests while fetching data.
     */
    @Test
    fun ensureOnlyOneRequestWhenFetchingMarketData() {
        val fetchingBean = UiDataBean.fetching(null)
        marketDataLiveData.postValue(fetchingBean)
        onView(withId(R.id.transactionDataProgress)).check(matches(not(isClickable())))
    }

    /**
     * Tests if we hide chart while fetching market data.
     */
    @Test
    fun ensureChartIsNotVisibleWhenFatchingData() {
        val fetchingBean = UiDataBean.fetching(null)
        marketDataLiveData.postValue(fetchingBean)
        onView(withId(R.id.transactionDataProgress)).check(matches(not(isDisplayed())))
    }

    /**
     * Tests if we show chart when we have available market data values.
     */
    @Test
    fun ensureChartIsVisibleWhenMarketValuesAvailable() {
        val marketValCount = 20
        val successBean = UiDataBean.success(UiTestsDataProvider.providMarketValues(marketValCount))
        marketDataLiveData.postValue(successBean)
        onView(withId(R.id.transactionDataProgress)).check(matches(isDisplayed()))
    }

    /**
     * Tests if we can show a toast message on an api or business error.
     */
    @Test
    fun ensureToastMessageIsShownToNotifyUser() {
        val errorMessage = "Unknown Error"
        val errorBean = UiDataBean.error(null, Error(0, errorMessage))
        marketDataLiveData.postValue(errorBean)
        onView(withText(errorMessage)).inRoot(withDecorView(not(`is`(homeFragment.activity?.window?.decorView))))
                .check(matches(isDisplayed()))
    }
}