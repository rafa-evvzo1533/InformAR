package com.alvear.informar.model

import kotlinx.datetime.Clock

data class Report(
    val id: String,
    val description: String,
    val reporterName: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val timestamp: Long = Clock.System.now().toEpochMilliseconds(),
    var isResolved: Boolean = false
)
