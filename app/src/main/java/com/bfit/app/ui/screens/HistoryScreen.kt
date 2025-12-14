package com.bfit.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bfit.app.Screen
import com.bfit.app.ui.components.BfitHeader

@Composable
fun HistoryScreen(navController: NavHostController) {

    // Estado simulado (luego podrá venir por navegación o ViewModel)
    val estadoTexto = "Moderado"

    // Color dinámico según estado
    val estadoColor = when (estadoTexto.lowercase()) {
        "óptimo", "optimo" -> Color(0xFF2E7D32)   // Verde
        "moderado" -> Color(0xFFFFA000)          // Ámbar
        "alerta" -> Color(0xFFFF7043)             // Naranja
        "crítico", "critico" -> Color(0xFFD32F2F) // Rojo
        else -> Color.Gray
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues())
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // HEADER BFIT (logo + título)
        BfitHeader(title = "Resumen de tu Evaluación")

        Spacer(modifier = Modifier.height(24.dp))

        // Fecha y hora
        Text(
            text = "14/12/2025 · 12:01",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Estado de salud (color dinámico)
        Text(
            text = estadoTexto.uppercase(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = estadoColor
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Explicación
        Text(
            text = "¿Qué significa?",
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Algunos indicadores se encuentran fuera del rango ideal.",
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Recomendación
        Text(
            text = "Recomendación",
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Se recomienda aumentar la actividad física y realizar pequeños ajustes en la dieta.",
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Botón INFO
        Button(
            onClick = { navController.navigate(Screen.Info.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("¿Qué significan estos indicadores?")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón VOLVER (Resultados)
        OutlinedButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Botón INICIO (reinicia el circuito)
        OutlinedButton(
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
