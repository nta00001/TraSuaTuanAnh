package com.example.trsahonghi.ui.home.bestselling

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.trsahonghi.api.model.Food
import com.example.trsahonghi.api.model.PopularItem
import com.example.trsahonghi.base.AppBehaviorOnServiceError
import com.example.trsahonghi.base.BasePresenter
import com.example.trsahonghi.base.BaseView

interface BestSellingContract {
    interface View : BaseView, AppBehaviorOnServiceError {

    }

    interface Presenter : BasePresenter {
        fun getUserName():LiveData<String>
        fun getListBestSelling()
        fun listBestSelling(): LiveData<List<Food>>
    }
}