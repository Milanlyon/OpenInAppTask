package com.applaunch.openinapptask.data.repo

import com.applaunch.openinapptask.DashboardResponse

interface DashboardRepo {

    suspend fun getAllData(): DashboardResponse?
}
