package com.example.trsahonghi.ui.info

import com.example.trsahonghi.base.AppBehaviorOnServiceError
import com.example.trsahonghi.base.BasePresenter
import com.example.trsahonghi.base.BaseView
import kotlinx.coroutines.flow.StateFlow

interface InfoContract {
    interface View : BaseView, AppBehaviorOnServiceError {

        fun openFragmentHistoryOrder()
    }

    interface Presenter : BasePresenter {
        fun userName(): StateFlow<String>
        fun phoneNumber(): StateFlow<String>

    }
}