package com.raqun.blockchaintracker.api

import com.raqun.blockchaintracker.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Created by tyln on 29.08.2018.
 */
class DefaultRequestInterceptor @Inject constructor()
    : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response? {
        chain?.let {
            return chain.proceed(with(chain.request().newBuilder()) {
                addHeader("Content-Type", "application/json")
                addHeader("VersionCode", BuildConfig.VERSION_CODE.toString())
                addHeader("VersionName", BuildConfig.VERSION_NAME)
                addHeader("ApplicationId", BuildConfig.APPLICATION_ID)
                build()
            })
        }

        return null
    }
}