package com.example.trsahonghi.api.repository.order

import com.example.trsahonghi.api.ApiService
import com.example.trsahonghi.api.RetrofitInstance
import com.example.trsahonghi.api.model.request.HistoryOrderRequest
import com.example.trsahonghi.api.model.request.OrderDetailRequest
import com.example.trsahonghi.api.model.response.BaseResponse
import com.example.trsahonghi.api.model.response.HistoryOrderResponse
import com.example.trsahonghi.api.model.response.OrderDetailResponse
import com.example.trsahonghi.util.Constants
import com.example.trsahonghi.util.SharedPreferencesUtils
import retrofit2.Call

class OrderRepositoryImpl(
    private val apiService: ApiService = RetrofitInstance.api
) : OrderRepository {
    override fun getHistoryOrder(): Call<BaseResponse<List<HistoryOrderResponse>>> {
        val historyOrderRequest =
            HistoryOrderRequest(userId = SharedPreferencesUtils.get(Constants.KEY.KEY_USER_ID, ""))
        return apiService.getHistoryOrder(historyOrderRequest)
    }

    override fun getOrderDetail(orderId: String): Call<BaseResponse<OrderDetailResponse>> {
        val orderDetailRequest = OrderDetailRequest(orderId)
        return apiService.getOrderDetail(orderDetailRequest)
    }

}