package com.bfit.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun InfoScreen(navController: NavHostController) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // TÍTULO
        Text(
            text = "Información de los indicadores",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(24.dp))

        // CONTENIDO
        Text(
            text =
                "IMC (Índice de Masa Corporal):\n" +
                        "Relaciona peso y altura para estimar si el peso es adecuado.\n\n" +

                        "BFIT 1:\n" +
                        "Evalúa la relación peso-altura de forma ajustada.\n\n" +

                        "BFIT 2:\n" +
                        "Considera la circunferencia de cintura para estimar riesgo metabólico.\n\n" +

                        "Estos indicadores permiten tener una visión general del estado de salud.",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        // BOTÓN VOLVER
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }
    }
}
