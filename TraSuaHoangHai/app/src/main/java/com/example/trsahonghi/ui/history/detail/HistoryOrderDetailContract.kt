package com.example.trsahonghi.ui.history.detail

import com.example.trsahonghi.api.model.response.OrderDetailResponse
import com.example.trsahonghi.base.AppBehaviorOnServiceError
import com.example.trsahonghi.base.BasePresenter
import com.example.trsahonghi.base.BaseView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface HistoryOrderDetailContract {
    interface View : BaseView, AppBehaviorOnServiceError {


    }

    interface Presenter : BasePresenter {
        fun getOrderDetail()
        fun oderDetail():StateFlow<OrderDetailResponse?>
    }
}