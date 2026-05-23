package com.alvear.informar

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alvear.informar.data.ReportRepository
import com.alvear.informar.ui.screens.HomeScreen
import com.alvear.informar.ui.screens.LoginScreen
import com.alvear.informar.ui.screens.ReportScreen
import com.alvear.informar.ui.screens.SplashScreen

@Composable
fun App(
    reportRepository: ReportRepository,
    mapContent: @Composable BoxScope.() -> Unit,
    onOpenNativeMap: () -> Unit
) {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val navController = rememberNavController()
            var userName by remember { mutableStateOf("") }
            var isAdmin by remember { mutableStateOf(false) }

            NavHost(navController = navController, startDestination = "splash") {
                composable("splash") {
                    SplashScreen(onNextScreen = {
                        navController.navigate("login") {
                            popUpTo("splash") { inclusive = true }
                        }
                    })
                }
                composable("login") {
                    LoginScreen(onLoginSuccess = { name, admin ->
                        userName = name
                        isAdmin = admin
                        navController.navigate("home") {
                            popUpTo("login") { inclusive = true }
                        }
                    })
                }
                composable("home") {
                    HomeScreen(
                        isAdmin = isAdmin,
                        reportRepository = reportRepository,
                        onNavigateToReport = { navController.navigate("report") },
                        onOpenMap = onOpenNativeMap
                    )
                }
                composable("report") {
                    ReportScreen(
                        userName = userName,
                        reportRepository = reportRepository,
                        onBack = { navController.popBackStack() },
                        mapContent = mapContent
                    )
                }
            }
        }
    }
}
