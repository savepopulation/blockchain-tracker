package com.raqun.blockchaintracker.extensions

import android.content.Context
import android.widget.Toast

/**
 * Created by tyln on 29.08.2018.
 */
fun Context.alert(message: String?) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()