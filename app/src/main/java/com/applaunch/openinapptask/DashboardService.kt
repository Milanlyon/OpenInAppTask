package com.applaunch.openinapptask

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface DashboardService {
     @GET("api/v1/dashboardNew")
     fun getAllDatas(@Header("Authorization") authorization: String): Call<DashboardResponse>
}