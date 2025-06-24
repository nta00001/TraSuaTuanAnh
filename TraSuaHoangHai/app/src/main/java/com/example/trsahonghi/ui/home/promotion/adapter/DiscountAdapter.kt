package com.example.trsahonghi.ui.home.promotion.adapter

import com.example.trsahonghi.R
import com.example.trsahonghi.api.model.Coupon
import com.example.trsahonghi.api.model.response.VoucherResponse
import com.example.trsahonghi.databinding.ItemCouponBinding
import com.example.trsahonghi.common.adapter.BaseRecyclerViewAdapter

class DiscountAdapter : BaseRecyclerViewAdapter<VoucherResponse, ItemCouponBinding>() {
    override val itemLayoutRes: Int
        get() = R.layout.item_coupon

    override fun onBindData(
        item: VoucherResponse,
        position: Int,
        viewDataBinding: ItemCouponBinding
    ) {
        viewDataBinding.apply {
            this.item = item
        }
    }
}