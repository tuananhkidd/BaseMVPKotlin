package com.beetech.candycart.base


import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.net.ConnectException
import java.net.UnknownHostException


open class BasePresenterImpl<T : BaseView>(mView: T) : BasePresenter {

    protected var mView: T? = mView
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    override fun start() {
        mView.let {
            it!!.initView()
        }
    }

    override fun destroy() {
        compositeDisposable.clear()
        mView = null
    }

    fun addDispose(dispose: Disposable) {
        compositeDisposable.add(dispose)
    }

}