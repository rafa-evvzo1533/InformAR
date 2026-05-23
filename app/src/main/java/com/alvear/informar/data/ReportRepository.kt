package com.alvear.informar.data

import com.alvear.informar.data.local.ReportDao
import com.alvear.informar.data.local.toDomain
import com.alvear.informar.data.local.toEntity
import com.alvear.informar.model.Report
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ReportRepository(private val reportDao: ReportDao) {

    fun getAllReports(): Flow<List<Report>> {
        return reportDao.getAllReports().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    suspend fun insertReport(report: Report) {
        reportDao.insertReport(report.toEntity())
    }

    suspend fun updateReportStatus(reportId: String, resolved: Boolean) {
        reportDao.updateReportStatus(reportId, resolved)
    }
}
