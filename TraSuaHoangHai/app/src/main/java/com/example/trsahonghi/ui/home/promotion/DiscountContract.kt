package com.example.trsahonghi.ui.home.promotion

import androidx.lifecycle.LiveData
import com.example.trsahonghi.api.model.Coupon
import com.example.trsahonghi.api.model.response.VoucherResponse
import com.example.trsahonghi.base.AppBehaviorOnServiceError
import com.example.trsahonghi.base.BasePresenter
import com.example.trsahonghi.base.BaseView

interface DiscountContract {
    interface View : BaseView, AppBehaviorOnServiceError {
        fun getStringRes(id: Int): String
    }

    interface Presenter : BasePresenter {
        fun getListVoucherByUser()
        fun listVoucher(): LiveData<List<VoucherResponse>>
        fun voucher(): LiveData<String>

    }
}