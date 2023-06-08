package com.applaunch.openinapptask.data.repo

import com.applaunch.openinapptask.DashboardResponse
import com.applaunch.openinapptask.data.remote.DashboardService
import com.applaunch.openinapptask.data.remote.RetrofitService

class DashboardRepoImpl : DashboardRepo {

    override suspend fun getAllData(): DashboardResponse? {

        val dashboardService =
            RetrofitService.getInstance().create<DashboardService>(DashboardService::class.java)

        return dashboardService.getAllDatas("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjU5MjcsImlhdCI6MTY3NDU1MDQ1MH0.dCkW0ox8tbjJA2GgUx2UEwNlbTZ7Rr38PVFJevYcXFI")
            .body()
    }
}
