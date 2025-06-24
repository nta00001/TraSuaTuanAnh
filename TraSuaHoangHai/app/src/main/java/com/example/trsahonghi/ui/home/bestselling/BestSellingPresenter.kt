package com.example.trsahonghi.ui.home.bestselling

import androidx.lifecycle.MutableLiveData
import com.example.trsahonghi.api.model.Food
import com.example.trsahonghi.api.repository.food.FoodRepository
import com.example.trsahonghi.base.CommonPresenter
import com.example.trsahonghi.util.Constants
import com.example.trsahonghi.util.SharedPreferencesUtils

class BestSellingPresenter(
    private val view: BestSellingContract.View,
    private val foodRepository: FoodRepository
) : CommonPresenter(view, view), BestSellingContract.Presenter {
    private var _listHotFood = MutableLiveData<List<Food>>()
    private var _userName = MutableLiveData(
        "Xin chào: ${SharedPreferencesUtils.get(Constants.KEY.KEY_USER_NAME, "")}"
    )

    override fun listBestSelling() = _listHotFood
    override fun getUserName() = _userName


    override fun getListBestSelling() {
        baseCallApi(foodRepository.getListHotFood(),
            onSuccess = { response ->
                _listHotFood.value = response?.data
            },
            onError = {

            })
    }
}