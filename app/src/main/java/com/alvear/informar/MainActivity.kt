package com.alvear.informar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.alvear.informar.data.ReportRepository
import com.alvear.informar.data.local.AppDatabase
import com.alvear.informar.ui.screens.HomeScreen
import com.alvear.informar.ui.screens.LoginScreen
import com.alvear.informar.ui.screens.ReportScreen
import com.alvear.informar.ui.screens.SplashScreen
import com.alvear.informar.ui.theme.InformARTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InformARTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    val database = remember { AppDatabase.getDatabase(context) }
                    val repository = remember { ReportRepository(database.reportDao()) }
                    
                    AppNavigation(repository)
                }
            }
        }
    }
}

@Composable
fun AppNavigation(reportRepository: ReportRepository) {
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
                onNavigateToReport = { navController.navigate("report") }
            )
        }
        composable("report") {
            ReportScreen(
                userName = userName,
                reportRepository = reportRepository,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
