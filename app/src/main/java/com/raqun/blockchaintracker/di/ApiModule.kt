package com.raqun.blockchaintracker.di

import com.raqun.blockchaintracker.BuildConfig
import com.raqun.blockchaintracker.api.ApiConstants
import com.raqun.blockchaintracker.api.DefaultRequestInterceptor
import com.raqun.blockchaintracker.api.TrackerServices
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by tyln on 29.08.2018.
 */
@Module
internal class ApiModule {

    @Provides
    @Singleton
    @Named("url")
    fun provideBaseUrl(): String = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideRequestInterceptor(): Interceptor = DefaultRequestInterceptor()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    @Singleton
    fun provideOkHttpClient(requestInterceptor: DefaultRequestInterceptor,
                            loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
            with(OkHttpClient.Builder()) {
                addInterceptor(requestInterceptor)
                if (BuildConfig.DEBUG) addInterceptor(loggingInterceptor)
                connectTimeout(ApiConstants.TIMEOUT_INMILIS, TimeUnit.MILLISECONDS)
                build()
            }

    @Provides
    @Singleton
    fun provideRetrofit(@Named("url") baseUrl: String, client: OkHttpClient): Retrofit =
            with(Retrofit.Builder()) {
                baseUrl(baseUrl)
                client(client)
                addConverterFactory(GsonConverterFactory.create())
                addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                build()
            }

    @Provides
    @Singleton
    fun provideApiServices(retrofit: Retrofit): TrackerServices = retrofit.create(TrackerServices::class.java)
}