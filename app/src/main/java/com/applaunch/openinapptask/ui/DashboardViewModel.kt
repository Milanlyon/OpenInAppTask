package com.applaunch.openinapptask.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.applaunch.openinapptask.DashboardResponse
import com.applaunch.openinapptask.data.repo.DashboardRepoImpl
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {
    private val dashboardRepo = DashboardRepoImpl()
    private val _dashboardLiveData = MutableLiveData<DashboardResponse>()
   val dashboardLiveData: LiveData<DashboardResponse> = _dashboardLiveData

    init {
        getAllData()
    }

    fun getAllData() {
        viewModelScope.launch {
            _dashboardLiveData.postValue(dashboardRepo.getAllData())
        }
    }


}