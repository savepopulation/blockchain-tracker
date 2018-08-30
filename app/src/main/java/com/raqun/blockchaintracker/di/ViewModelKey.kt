package com.raqun.blockchaintracker.di

import android.arch.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Created by tyln on 29.08.2018.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)
