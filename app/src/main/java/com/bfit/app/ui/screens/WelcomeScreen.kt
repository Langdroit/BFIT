package com.bfit.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bfit.app.Screen
import com.bfit.app.ui.components.BfitLogo


@Composable
fun WelcomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BfitLogo()


        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            navController.navigate(Screen.Evaluation.route)
        }) {
            Text("Comenzar evaluación")
        }
    }
}
