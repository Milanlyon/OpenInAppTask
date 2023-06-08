package com.applaunch.openinapptask

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DashboardRepo {

    fun getAllData(apiResponse: ApiResponse) {

        val dashboardService = RetrofitService.getInstance().create<DashboardService>(DashboardService::class.java)

        var call: Call<DashboardResponse> = dashboardService.getAllDatas("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjU5MjcsImlhdCI6MTY3NDU1MDQ1MH0.dCkW0ox8tbjJA2GgUx2UEwNlbTZ7Rr38PVFJevYcXFI")
        call.enqueue(object : Callback<DashboardResponse> {
            override fun onResponse(
                call: Call<DashboardResponse>,
                response: Response<DashboardResponse>
            ) {
                if (response.isSuccessful) {
                    apiResponse.onSuccess(response.body()!!)

                } else {
                    Log.d("tag", "" + response.message())
                }
            }

            override fun onFailure(call: Call<DashboardResponse>, t: Throwable) {
                Log.d("tag", "" + t.message)
            }
        })
    }

}

interface ApiResponse {
    fun onSuccess(dashboardResponse: DashboardResponse)
    fun onFailure(errorMsg: String)
}