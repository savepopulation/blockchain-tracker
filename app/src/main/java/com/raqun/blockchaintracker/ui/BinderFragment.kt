package com.raqun.blockchaintracker.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import javax.inject.Inject

/**
 * Created by tyln on 29.08.2018.
 */
abstract class BinderFragment<VB : ViewDataBinding, VM : ViewModel> : BaseFragment() {

    @VisibleForTesting
    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    protected lateinit var binding: VB

    @VisibleForTesting
    lateinit var viewModel: VM

    abstract fun getModelClass(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, vmFactory).get(getModelClass())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
        initView()
        return binding.root
    }

    open protected fun initView() {
        // Can be overridden from subclasses
    }
}