package com.alvear.informar.model

data class Report(
    val id: String,
    val description: String,
    val reporterName: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val timestamp: Long = System.currentTimeMillis(),
    var isResolved: Boolean = false
)
