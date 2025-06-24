package com.example.trsahonghi.ui.home.promotion

import androidx.lifecycle.MutableLiveData
import com.example.trsahonghi.R
import com.example.trsahonghi.api.model.response.VoucherResponse
import com.example.trsahonghi.api.repository.food.FoodRepository
import com.example.trsahonghi.base.CommonPresenter
import com.example.trsahonghi.util.Constants
import com.example.trsahonghi.util.SharedPreferencesUtils

class DiscountPresenter(
    private val view: DiscountContract.View,
    private val foodRepository: FoodRepository
) : CommonPresenter(view, view), DiscountContract.Presenter {

    private val _listVoucher = MutableLiveData<List<VoucherResponse>>()
    private val _voucher = MutableLiveData<String>()
    override fun getListVoucherByUser() {
        val userID = SharedPreferencesUtils.get(Constants.KEY.KEY_USER_ID, "")
        baseCallApi(foodRepository.getListVoucherByUser(userID),
            onSuccess = { response ->
                _listVoucher.value = response?.data
                _voucher.value = _listVoucher.value?.firstOrNull()?.description
                    ?: view.getStringRes(R.string.no_discount_code)

            },
            onError = {

            })
    }

    override fun listVoucher() = _listVoucher

    override fun voucher() = _voucher
}