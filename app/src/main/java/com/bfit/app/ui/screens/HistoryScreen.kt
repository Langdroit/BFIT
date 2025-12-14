package com.bfit.app.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bfit.app.Screen
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun HistoryScreen(navController: NavHostController) {

    // 🔹 Fecha y hora actuales (síntesis de la última evaluación)
    val fechaHora = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy – HH:mm")
    val fechaFormateada = fechaHora.format(formatter)

    // 🔹 Estado de salud (síntesis)
    // Nota: en esta versión se muestra como texto fijo explicativo.
    // En una versión futura podría recibirse como argumento.
    val estado = "Moderado"

    // 🔹 Explicación y recomendación simples según el estado
    val explicacion: String
    val recomendacion: String

    when (estado) {
        "Óptimo" -> {
            explicacion = "Tus indicadores se encuentran dentro de los rangos saludables."
            recomendacion = "Mantené tu nivel actual de actividad física y una dieta equilibrada."
        }
        "Moderado" -> {
            explicacion = "Algunos indicadores se encuentran fuera del rango ideal."
            recomendacion = "Se recomienda aumentar la actividad física y realizar pequeños ajustes en la dieta."
        }
        "Alerta" -> {
            explicacion = "Varios indicadores muestran riesgo para la salud."
            recomendacion = "Se recomienda combinar ejercicio regular con cambios en la alimentación."
        }
        else -> {
            explicacion = "Los indicadores muestran un riesgo elevado para la salud."
            recomendacion = "Se recomienda consultar con un profesional de la salud y realizar cambios en el estilo de vida."
        }
    }

    val contexto = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Resumen de tu evaluación",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text("Fecha y hora:")
        Text(fechaFormateada, style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Text("Estado de salud:")
        Text(estado, style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Text("¿Qué significa?")
        Text(explicacion)

        Spacer(modifier = Modifier.height(16.dp))

        Text("Recomendación")
        Text(recomendacion)

        Spacer(modifier = Modifier.height(32.dp))

        // 🔹 Botón copiar resumen
        Button(
            onClick = {
                val resumen = """
                    BFIT – Evaluación de salud
                    Fecha: $fechaFormateada
                    Estado: $estado
                    $explicacion
                    Recomendación: $recomendacion
                """.trimIndent()

                val clipboard = contexto.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("Resumen BFIT", resumen)
                clipboard.setPrimaryClip(clip)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Copiar resumen")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate(Screen.Welcome.route)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Volver al inicio")
        }
    }
}
