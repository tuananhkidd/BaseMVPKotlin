package com.beetech.candycart.util

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService


object DeviceUtil {
    fun hideSoftKeyboard(mActivity: Activity) {
        val view = mActivity.currentFocus
        if (view != null) {
            val inputManager = mActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun showKeyboard(activity: Activity) {
    }

    fun hasNetworkConnection(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}