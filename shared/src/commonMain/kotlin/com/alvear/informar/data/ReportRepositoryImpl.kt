package com.alvear.informar.data

import com.alvear.informar.data.local.ReportDao
import com.alvear.informar.data.local.toDomain
import com.alvear.informar.data.local.toEntity
import com.alvear.informar.model.Report
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ReportRepositoryImpl(private val reportDao: ReportDao) : ReportRepository {
    override fun getAllReports(): Flow<List<Report>> {
        return reportDao.getAllReports().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertReport(report: Report) {
        reportDao.insertReport(report.toEntity())
    }

    override suspend fun updateReportStatus(id: String, isResolved: Boolean) {
        reportDao.updateReportStatus(id, isResolved)
    }
}
