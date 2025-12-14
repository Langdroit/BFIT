package com.bfit.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bfit.app.Screen

@Composable
fun ResultScreen(
    navController: NavHostController,
    imcResultado: String,
    bfit1Resultado: String,
    bfit2Resultado: String,
    estadoGlobal: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        // 🔹 Título centrado
        Text(
            text = "Resultados BFIT",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        // 🔹 Encabezados de columnas
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Tu evaluación", style = MaterialTheme.typography.titleMedium)
            Text("Valores ideales", style = MaterialTheme.typography.titleMedium)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Divider()
        Spacer(modifier = Modifier.height(16.dp))

        // 🔹 Fila IMC
        ResultRow(
            leftText = imcResultado,
            rightText = "IMC: 18.5 – 24.9"
        )

        // 🔹 Fila BFIT 1
        ResultRow(
            leftText = bfit1Resultado,
            rightText = "BFIT 1: Relación peso/altura saludable"
        )

        // 🔹 Fila BFIT 2
        ResultRow(
            leftText = bfit2Resultado,
            rightText = "BFIT 2: Cintura dentro del rango normal"
        )

        // 🔹 Fila Estado global
        ResultRow(
            leftText = estadoGlobal,
            rightText = "Estado esperado: Óptimo"
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { navController.navigate(Screen.History.route) },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Ver historial")
        }
    }
}

@Composable
fun ResultRow(leftText: String, rightText: String) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = leftText,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = rightText,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
        Divider(thickness = 0.5.dp)
        Spacer(modifier = Modifier.height(12.dp))
    }
}
