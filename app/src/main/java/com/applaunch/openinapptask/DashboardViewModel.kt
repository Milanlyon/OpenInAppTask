package com.applaunch.openinapptask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {
    private val dashboardRepo = DashboardRepo()
    private val _dashboardLiveData = MutableLiveData<DashboardResponse>()
    val liveData: LiveData<DashboardResponse> = _dashboardLiveData

    fun getAllData() {

        dashboardRepo.getAllData(object : ApiResponse {
            override fun onSuccess(dashboardResponse: DashboardResponse) {
                _dashboardLiveData.postValue(dashboardResponse)
            }

            override fun onFailure(errorMsg: String) {
            }

        })
    }


}