package com.beetech.candycart.view.impl;

import androidx.recyclerview.widget.LinearLayoutManager
import com.beetech.candycart.R
import com.beetech.candycart.adapter.ListProductAdapter
import com.beetech.candycart.base.BaseActivity
import com.beetech.candycart.custom.BaseLoadMoreRecyclerView
import com.beetech.candycart.data.ProductResponse
import com.beetech.candycart.presenter.MainPresenter
import com.beetech.candycart.presenter.impl.MainPresenterImpl
import com.beetech.candycart.view.MainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainPresenter>(), MainView<ProductResponse> {
    override fun showData(data: ArrayList<ProductResponse>, isRefreshing: Boolean) {
        if (isRefreshing) {
            rcv_data.refreshItem(data)
        } else {
            rcv_data.addItem(data)
        }
    }

    override fun enableLoadmore(enable: Boolean) {
        rcv_data.enableLoadMore(enable)
    }

    override val layoutRes: Int
        get() = R.layout.activity_main

    override fun getPresenter(): MainPresenter {
        return MainPresenterImpl(this)
    }

    override fun initView() {
        mPresenter?.getCategory(true)
        rcv_data.setAdapter(ListProductAdapter(this))
        rcv_data.setGridLayoutManager(3)
        rcv_data.setLoadMoreListener(object : BaseLoadMoreRecyclerView.OnBaseLoadMoreListener {
            override fun onLoadMore() {
                mPresenter?.getCategory(false)
            }
        })

        rcv_data.setRefreshListener(object : BaseLoadMoreRecyclerView.OnBaseRefreshListener {
            override fun onRefresh() {
                mPresenter?.getCategory(true)
            }
        })
    }
}
