package com.example.trsahonghi.ui.home.listfood.bottomsheet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.trsahonghi.api.model.Food
import com.example.trsahonghi.base.BasePresenter
import com.example.trsahonghi.base.BaseView

interface IngredientTypeContract {

    interface View : BaseView {

    }

    interface Presenter : BasePresenter {
        fun updateFood()
        fun subQuantity()
        fun addQuantity()
        fun setSize(size: String)
        fun food(): LiveData<Food>
        fun size(): LiveData<String>
        fun quantity(): MutableLiveData<String>
    }
}