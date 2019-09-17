package com.beetech.candycart

import android.app.Application
import android.content.Context
import com.deploygate.sdk.DeployGate

class CandyCartAppplication : Application() {
    companion object {
        lateinit var instance: CandyCartAppplication
            private set
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        DeployGate.install(this)
    }

    fun getContext(): Context {
        return this
    }
}