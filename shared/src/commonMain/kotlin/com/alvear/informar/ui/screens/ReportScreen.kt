package com.alvear.informar.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.alvear.informar.data.ReportRepository
import com.alvear.informar.model.Report
import com.alvear.informar.generateUuid
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportScreen(
    userName: String, 
    reportRepository: ReportRepository, 
    onBack: () -> Unit,
    mapContent: @Composable BoxScope.() -> Unit
) {
    var description by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nuevo Reporte") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Reportando como: $userName",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Dirección (ej: Bartolomé Mitre 431)") },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Calle y altura opcional") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descripción del problema") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 2
            )
            Spacer(modifier = Modifier.height(16.dp))
            
            Text("Ajustá la ubicación exacta:")
            Spacer(modifier = Modifier.height(8.dp))
            
            Box(modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant)) {
                mapContent()
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Button(
                onClick = { 
                    scope.launch {
                        val newReport = Report(
                            id = generateUuid(),
                            description = description,
                            reporterName = userName,
                            address = address,
                            latitude = -36.03, // Aquí se debería pasar la lat/lng del mapa
                            longitude = -60.01
                        )
                        reportRepository.insertReport(newReport)
                        onBack()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = description.isNotBlank() && address.isNotBlank()
            ) {
                Text("Enviar Reporte")
            }
        }
    }
}
