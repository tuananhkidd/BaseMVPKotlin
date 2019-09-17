package com.beetech.candycart.custom

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.beetech.candycart.R


class CarBookingLoadingDialog private constructor(private val context: Context?) {

    private var dialog: AlertDialog? = null

    init {
        if (context != null && !shown) {
            val dialogBuilder = AlertDialog.Builder(context)
            val li = LayoutInflater.from(context)
            val dialogView = li.inflate(R.layout.layout_loading, null)
            dialogBuilder.setView(dialogView)
            dialogBuilder.setCancelable(false)
            dialog = dialogBuilder.create()
            if (dialog!!.window != null) {
                dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
            dialog!!.setCancelable(false)
            dialog!!.setCanceledOnTouchOutside(false)
        }
    }

    fun show() {
        if (!(context as Activity).isFinishing) {
            if (!shown && dialog != null) {
                forceShown()
                dialog!!.show()
            }
        }
    }

    fun hidden() {
        if (shown && dialog != null && dialog!!.isShowing) {
            initialize()
            dialog!!.dismiss()
        }
    }

    private fun forceShown() {
        shown = true
    }

    private fun initialize() {
        shown = false
    }

    fun destroyLoadingDialog() {
        if (instance != null && instance!!.dialog != null) {
            instance!!.dialog!!.dismiss()
        }
        instance = null
    }

    companion object {

        private var shown = false

        private var instance: CarBookingLoadingDialog? = null

        fun getInstance(context: Context): CarBookingLoadingDialog {
            instance = CarBookingLoadingDialog(context)
            return instance!!
        }
    }
}
