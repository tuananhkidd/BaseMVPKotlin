package com.beetech.candycart.base

interface BaseLoadmoreView<T> : BaseView {
    fun enableLoadmore(enable:Boolean)
    fun showData(data:ArrayList<T>,isRefreshing:Boolean)
}