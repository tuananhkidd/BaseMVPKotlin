package com.beetech.candycart.presenter.impl;


import com.beetech.candycart.base.BasePresenterImpl
import com.beetech.candycart.data.ProductResponse
import com.beetech.candycart.network.RetrofitManager
import com.beetech.candycart.presenter.MainPresenter
import com.beetech.candycart.view.MainView

public class MainPresenterImpl(view: MainView<ProductResponse>) : BasePresenterImpl<MainView<ProductResponse>>(view), MainPresenter {
    private var pageIndex = 1
    override fun getCategory(isRefresing:Boolean) {
        if(isRefresing){
            pageIndex = 1
        }
        addDispose(
            RetrofitManager.getCategory(pageIndex).doOnSubscribe {
                mView?.showLoading()
            }
                .doFinally {
                    mView?.hideLoading()
                }
                .subscribe(
                    {
                        pageIndex++
                        mView?.enableLoadmore(pageIndex<=it.totalPage)
                        mView?.showData(it.data,isRefresing)
                    },
                    {

                    }
                ))
    }


    override fun start() {
        super.start()
    }

    override fun destroy() {
        super.destroy()
    }
}