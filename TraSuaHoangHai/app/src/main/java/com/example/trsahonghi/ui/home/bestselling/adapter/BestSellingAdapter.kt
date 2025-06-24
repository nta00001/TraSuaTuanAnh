package com.example.trsahonghi.ui.home.bestselling.adapter

import android.graphics.drawable.BitmapDrawable
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.trsahonghi.R
import com.example.trsahonghi.api.Config
import com.example.trsahonghi.api.model.Food
import com.example.trsahonghi.api.model.PopularItem
import com.example.trsahonghi.databinding.ItemBestSellingBinding
import com.example.trsahonghi.common.adapter.BaseRecyclerViewAdapter

class BestSellingAdapter(
    val onClickListener: ((Food, Int) -> Unit)? = null
) : BaseRecyclerViewAdapter<Food, ItemBestSellingBinding>() {

    override val itemLayoutRes: Int = R.layout.item_best_selling

    override fun onBindData(
        item: Food,
        position: Int,
        viewDataBinding: ItemBestSellingBinding
    ) {
        viewDataBinding.apply {
            Glide.with(ivBestSelling.context)
                .load(Config.urlImage + item.imageUrl)
                .placeholder(R.drawable.ic_null)
                .error(R.drawable.ic_null)
                .into(ivBestSelling)

            txtName.text = item.foodName
        }
    }
}