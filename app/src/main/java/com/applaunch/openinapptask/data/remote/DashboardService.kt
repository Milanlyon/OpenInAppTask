package com.applaunch.openinapptask.data.remote

import com.applaunch.openinapptask.DashboardResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface DashboardService {
     @GET("api/v1/dashboardNew")
     suspend fun getAllDatas(@Header("Authorization") authorization: String): Response<DashboardResponse>
}