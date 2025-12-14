package com.bfit.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bfit.app.Screen

@Composable
fun HistoryScreen(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // TÍTULO
        Text(
            text = "Resumen de tu evaluación",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(24.dp))

        // FECHA
        Text(
            text = "Fecha y hora:\n14/12/2025 – 12:01",
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ESTADO
        Text(
            text = "Estado de salud:\nModerado",
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // EXPLICACIÓN
        Text(
            text = "¿Qué significa?\n" +
                    "Algunos indicadores se encuentran fuera del rango ideal.",
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // RECOMENDACIÓN
        Text(
            text = "Recomendación:\n" +
                    "Se recomienda aumentar la actividad física y realizar " +
                    "pequeños ajustes en la dieta.",
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        // BOTÓN INFO
        Button(
            onClick = { navController.navigate(Screen.Info.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("¿Qué significan estos indicadores?")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // BOTÓN VOLVER (pantalla anterior: Resultados)
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // BOTÓN INICIO (reinicia el circuito)
        Button(
            onClick = {
                navController.navigate(Screen.Welcome.route) {
                    popUpTo(Screen.Welcome.route) { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Inicio")
        }
    }
}
