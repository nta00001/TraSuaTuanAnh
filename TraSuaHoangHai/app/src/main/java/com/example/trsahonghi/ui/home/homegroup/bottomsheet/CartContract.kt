package com.example.trsahonghi.ui.home.homegroup.bottomsheet

import androidx.lifecycle.LiveData
import com.example.trsahonghi.api.model.BubbleTea
import com.example.trsahonghi.api.model.Food
import com.example.trsahonghi.base.BasePresenter
import com.example.trsahonghi.base.BaseView

interface CartContract {
    interface View : BaseView {

    }

    interface Presenter : BasePresenter {
        fun listFood(): LiveData<List<Food>>
        fun addAmountFood(food: Food)
        fun subAmountFood(food: Food)
        fun updateFood(food: Food)
        fun removeFood(position: Int)

    }
}