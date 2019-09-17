package com.beetech.candycart.network

import android.content.Context
import com.beetech.candycart.R
import com.beetech.candycart.util.DeviceUtil
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class NetworkCheckerInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (DeviceUtil.hasNetworkConnection(context)) {
            return chain.proceed(chain.request())
        } else {
            throw NoConnectivityException()
        }
    }

    inner class NoConnectivityException : IOException() {
        override val message: String
            get() = context.resources.getString(R.string.no_internet_connection)
    }

}