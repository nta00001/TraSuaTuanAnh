package com.example.trsahonghi.ui.history

import com.example.trsahonghi.api.model.response.HistoryOrderResponse
import com.example.trsahonghi.base.AppBehaviorOnServiceError
import com.example.trsahonghi.base.BasePresenter
import com.example.trsahonghi.base.BaseView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


interface HistoryOrderContract {
    interface View : BaseView, AppBehaviorOnServiceError {


    }

    interface Presenter : BasePresenter {

        fun getHistoryOrder()
        fun orderList(): MutableStateFlow<List<HistoryOrderResponse>>

        fun filteredList(): StateFlow<List<HistoryOrderResponse>>
        fun setOrderStatus(status: Set<String>)


    }
}