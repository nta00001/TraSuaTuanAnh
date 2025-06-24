package com.example.trsahonghi.api.repository.account

import com.example.trsahonghi.api.ApiService
import com.example.trsahonghi.api.RetrofitInstance
import com.example.trsahonghi.api.model.request.LoginRequest
import com.example.trsahonghi.api.model.request.RegisterRequest
import com.example.trsahonghi.api.model.response.AccountResponse
import com.example.trsahonghi.api.model.response.BaseResponse
import com.example.trsahonghi.api.model.response.LoginResponse
import com.example.trsahonghi.api.model.response.RegisterResponse
import retrofit2.Call

class AccountRepositoryImpl(
    private val apiService: ApiService = RetrofitInstance.api
) : AccountRepository {

    override fun register(
        phoneNumber: String?,
        userName: String?,
        password: String?,
    ): Call<BaseResponse<RegisterResponse>> {
        return apiService.registerAccount(
            RegisterRequest(
                phoneNumber,
                userName,
                password,

            )
        )
    }

    override fun login(phoneNumber: String?, password: String?): Call<BaseResponse<LoginResponse>> {
        return apiService.loginAccount(
            LoginRequest(
                phoneNumber,
                password
            )
        )
    }

    override fun loginToken(): Call<BaseResponse<LoginResponse>> {
        return apiService.loginTokenAccount()
    }

    override fun getAccount(): Call<BaseResponse<AccountResponse>> {
        return apiService.getAccount()
    }
}