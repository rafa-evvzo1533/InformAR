package com.alvear.informar.data

import com.alvear.informar.model.Report
import kotlinx.coroutines.flow.Flow

interface ReportRepository {
    fun getAllReports(): Flow<List<Report>>
    suspend fun insertReport(report: Report)
    suspend fun updateReportStatus(id: String, isResolved: Boolean)
}
