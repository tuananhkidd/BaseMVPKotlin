package com.beetech.candycart.adapter

import android.content.Context
import android.graphics.Paint
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.beetech.candycart.R
import com.beetech.candycart.custom.EndlessLoadingRecyclerViewAdapter
import com.beetech.candycart.data.ProductResponse
import kotlinx.android.synthetic.main.item_home.view.*

class ListProductAdapter(context: Context) : EndlessLoadingRecyclerViewAdapter(context, false) {
    override fun initLoadingViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return LoadingViewHolder(inflater.inflate(R.layout.item_load_more, parent, false))
    }

    override fun bindLoadingViewHolder(loadingViewHolder: LoadingViewHolder, position: Int) {
    }

    override fun initNormalViewHolder(parent: ViewGroup): RecyclerView.ViewHolder? {
        return ListProductViewHolder(inflater.inflate(R.layout.item_home, parent, false))
    }

    override fun bindNormalViewHolder(normalVH: NormalViewHolder, position: Int) {
        val data: ProductResponse = getItem(position, ProductResponse::class.java)!!
        val holder = normalVH as ListProductViewHolder
        holder.imvLogo.text = data.name
    }

    inner class ListProductViewHolder(itemView: View) : NormalViewHolder(itemView) {
        val imvLogo = itemView.tv_title
    }

    interface OnActionProductListener {
        fun onDesc(pos: Int)
        fun onIncrease(position: Int)
        fun onLike(position: Int)
        fun onAddToCart(position: Int, targetView: ImageView)
    }
}