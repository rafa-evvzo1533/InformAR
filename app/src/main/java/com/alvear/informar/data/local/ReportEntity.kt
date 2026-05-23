package com.alvear.informar.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alvear.informar.model.Report

@Entity(tableName = "reports")
data class ReportEntity(
    @PrimaryKey val id: String,
    val description: String,
    val reporterName: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val timestamp: Long,
    val isResolved: Boolean
)

fun ReportEntity.toDomain(): Report {
    return Report(
        id = id,
        description = description,
        reporterName = reporterName,
        address = address,
        latitude = latitude,
        longitude = longitude,
        timestamp = timestamp,
        isResolved = isResolved
    )
}

fun Report.toEntity(): ReportEntity {
    return ReportEntity(
        id = id,
        description = description,
        reporterName = reporterName,
        address = address,
        latitude = latitude,
        longitude = longitude,
        timestamp = timestamp,
        isResolved = isResolved
    )
}
