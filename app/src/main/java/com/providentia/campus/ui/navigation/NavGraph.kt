package com.providentia.campus.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.providentia.campus.ui.auth.LoginScreen
import com.providentia.campus.ui.home.HomeScreen
import com.providentia.campus.ui.onboarding.WelcomeScreen
import com.providentia.campus.ui.screening.ScreeningScreen

@Composable
fun ProvidentiaNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") {
            WelcomeScreen(
                onGetStarted = {
                    navController.navigate("login") {
                        popUpTo("welcome") { inclusive = true }
                    }
                }
            )
        }
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("screening") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }
        composable("screening") {
            ScreeningScreen(
                onScreeningComplete = { score ->
                    // Here we could pass the score to Home or save it
                    navController.navigate("home") {
                        popUpTo("screening") { inclusive = true }
                    }
                },
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }
        composable("home") {
            HomeScreen()
        }
    }
}
