package com.example.trsahonghi.ui.history.adapter

import com.bumptech.glide.Glide
import com.example.trsahonghi.R
import com.example.trsahonghi.api.Config
import com.example.trsahonghi.api.model.Food
import com.example.trsahonghi.common.adapter.BaseRecyclerViewAdapter
import com.example.trsahonghi.databinding.ItemOrderDetailHistoryBinding

class OrderDetailAdapter : BaseRecyclerViewAdapter<Food, ItemOrderDetailHistoryBinding>() {
    override val itemLayoutRes: Int
        get() = R.layout.item_order_detail_history

    override fun onBindData(
        item: Food,
        position: Int,
        viewDataBinding: ItemOrderDetailHistoryBinding
    ) {
        viewDataBinding.apply {
            this.item = item
            executePendingBindings()
            Glide.with(ivTea.context)
                .load(Config.urlImage + item.imageUrl)
                .placeholder(R.drawable.ic_null)
                .error(R.drawable.ic_null)
                .into(ivTea)
        }
    }
}