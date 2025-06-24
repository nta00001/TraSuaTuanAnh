package com.example.trsahonghi.api.repository.order

import com.example.trsahonghi.api.model.request.HistoryOrderRequest
import com.example.trsahonghi.api.model.response.BaseResponse
import com.example.trsahonghi.api.model.response.HistoryOrderResponse
import com.example.trsahonghi.api.model.response.OrderDetailResponse
import retrofit2.Call

interface OrderRepository {
    fun getHistoryOrder(): Call<BaseResponse<List<HistoryOrderResponse>>>
    fun getOrderDetail(orderId: String): Call<BaseResponse<OrderDetailResponse>>
}