package com.example.trsahonghi.ui.home.promotion.detail

import androidx.lifecycle.LiveData
import com.example.trsahonghi.api.model.Coupon
import com.example.trsahonghi.api.model.response.VoucherResponse
import com.example.trsahonghi.base.AppBehaviorOnServiceError
import com.example.trsahonghi.base.BasePresenter
import com.example.trsahonghi.base.BaseView

interface DiscountDetailContract {
    interface View: BaseView, AppBehaviorOnServiceError {
        fun getStringRes(id: Int): String

    }
    interface Presenter: BasePresenter {

        fun getListVoucher()
        fun listVoucher(): LiveData<List<VoucherResponse>>
        fun coupon(): LiveData<String>

    }
}