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
import com.bfit.app.ui.components.BfitHeader

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
            .padding(WindowInsets.systemBars.asPaddingValues())
            .padding(horizontal = 24.dp)
    ) {

        /* ---------- HEADER ---------- */
        BfitHeader(title = "Resultados")

        Spacer(modifier = Modifier.height(24.dp))

        /* ---------- ENCABEZADOS ---------- */
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Tu evaluación",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Valores ideales",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.End
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
        Divider()
        Spacer(modifier = Modifier.height(12.dp))

        /* ---------- RESULTADOS ---------- */
        ResultRow(
            leftText = imcResultado,
            rightText = "IMC: 18.5 – 24.9"
        )

        ResultRow(
            leftText = bfit1Resultado,
            rightText = "Relación peso / altura saludable"
        )

        ResultRow(
            leftText = bfit2Resultado,
            rightText = "Cintura dentro del rango normal"
        )

        ResultRow(
            leftText = estadoGlobal,
            rightText = "Estado esperado: Óptimo"
        )

        Spacer(modifier = Modifier.height(32.dp))

        /* ---------- BOTÓN ---------- */
        Button(
            onClick = { navController.navigate(Screen.History.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver resumen")
        }
    }
}

@Composable
fun ResultRow(
    leftText: String,
    rightText: String
) {
    Column {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
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
