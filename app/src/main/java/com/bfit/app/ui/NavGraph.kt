package com.bfit.app.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bfit.app.Screen
import com.bfit.app.ui.screens.*

@Composable
fun AppNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route
    ) {

        composable(Screen.Welcome.route) {
            WelcomeScreen(navController)
        }

        composable(Screen.Evaluation.route) {
            EvaluationScreen(navController)
        }

        composable(
            route = Screen.Result.route + "/{imc}/{bfit1}/{bfit2}/{estado}",
            arguments = listOf(
                navArgument("imc") { type = NavType.StringType },
                navArgument("bfit1") { type = NavType.StringType },
                navArgument("bfit2") { type = NavType.StringType },
                navArgument("estado") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val imc = backStackEntry.arguments?.getString("imc") ?: ""
            val bfit1 = backStackEntry.arguments?.getString("bfit1") ?: ""
            val bfit2 = backStackEntry.arguments?.getString("bfit2") ?: ""
            val estado = backStackEntry.arguments?.getString("estado") ?: ""

            ResultScreen(
                navController = navController,
                imcResultado = imc,
                bfit1Resultado = bfit1,
                bfit2Resultado = bfit2,
                estadoGlobal = estado
            )
        }

        composable(Screen.History.route) {
            HistoryScreen(navController)
        }

        composable(Screen.Info.route) {
            InfoScreen(navController)
        }
    }
}
