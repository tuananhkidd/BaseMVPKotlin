package com.beetech.candycart.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.beetech.candycart.R
import kotlinx.android.synthetic.main.layout_base_loadmore_recyclerview.view.*

class BaseLoadMoreRecyclerView : RelativeLayout, EndlessLoadingRecyclerViewAdapter.OnLoadingMoreListener, SwipeRefreshLayout.OnRefreshListener, RecyclerViewAdapter.OnItemClickListener {
    private var mContext: Context? = null
    private var mAdapter: EndlessLoadingRecyclerViewAdapter? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private var loadMoreListener: OnBaseLoadMoreListener? = null
    private var refreshListener: OnBaseRefreshListener? = null
    private var clickListener: OnBaseItemClickListener? = null

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
        setParams(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
        setParams(attrs)
    }

    private fun setParams(attrs: AttributeSet) {
        val a = mContext!!.theme.obtainStyledAttributes(attrs, R.styleable.BaseLoadMoreRecyclerView, 0, 0)

        val padding = a.getDimension(R.styleable.BaseLoadMoreRecyclerView_blmrcv_padding,0f).toInt()
        rcv.setPadding(padding,padding,padding,padding)
        a.recycle()
    }

    private fun init(context: Context) {
        mContext = context
        val view = LayoutInflater.from(context).inflate(R.layout.layout_base_loadmore_recyclerview, this, true)
        swipeRefresh.setOnRefreshListener(this)
    }

    fun setLinearLayoutManager(orientation: Int) {
        mLayoutManager = LinearLayoutManager(context, orientation, false)
        rcv.layoutManager = mLayoutManager

    }

    fun setGridLayoutManager(spanCount:Int) {
        mLayoutManager = GridLayoutManager(context, spanCount)
        (mLayoutManager as GridLayoutManager).spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (mAdapter!!.getItemViewType(position) == EndlessLoadingRecyclerViewAdapter.VIEW_TYPE_LOADING) spanCount else 1
            }
        }
        rcv.layoutManager = mLayoutManager
    }

    fun setAdapter(adapter: EndlessLoadingRecyclerViewAdapter) {
        this.mAdapter = adapter
        mAdapter?.setLoadingMoreListener(this)
        mAdapter?.addOnItemClickListener(this)
        rcv.adapter = mAdapter
    }

    fun setLoadMoreListener(listener: OnBaseLoadMoreListener) {
        this.loadMoreListener = listener
    }

    fun setRefreshListener(listener: OnBaseRefreshListener) {
        this.refreshListener = listener
    }

    fun setOnClickListener(listener: OnBaseItemClickListener) {
        this.clickListener = listener
    }

    fun refreshItem(data: List<Any>) {
        mAdapter?.refresh(data)
        swipeRefresh.isRefreshing = false
    }

    fun addItem(data: List<Any>) {
        mAdapter?.hideLoadingItem()
        mAdapter?.addModels(data, false)
    }

    fun enableLoadMore(enable:Boolean){
        mAdapter?.enableLoadingMore(enable)
    }
    override fun onLoadMore() {
        mAdapter?.showLoadingItem(true)
        loadMoreListener?.onLoadMore()
    }

    override fun onRefresh() {
        swipeRefresh.isRefreshing = true
        refreshListener?.onRefresh()
    }

    override fun onItemClick(adapter: RecyclerView.Adapter<*>, viewHolder: RecyclerView.ViewHolder?, viewType: Int, position: Int) {
        clickListener?.onItemClick(viewType, position)
    }


    interface OnBaseLoadMoreListener {
        fun onLoadMore()
    }

    interface OnBaseRefreshListener {
        fun onRefresh()
    }

    interface OnBaseItemClickListener {
        fun onItemClick(viewType: Int, position: Int)
    }

}