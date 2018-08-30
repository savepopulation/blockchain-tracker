package com.raqun.blockchaintracker.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.raqun.blockchaintracker.R
import com.raqun.blockchaintracker.extensions.transact

class MainActivity : BaseActivity() {

    private var navigationController: NavigationController? = null

    override fun getLayoutRes(): Int = R.layout.activity_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigationController = NavigationController(this)
        if (savedInstanceState == null) {
            navigationController?.showHome()
        }
    }

    override fun onDestroy() {
        navigationController = null
        super.onDestroy()
    }
}
