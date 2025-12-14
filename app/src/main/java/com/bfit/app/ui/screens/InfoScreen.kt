package com.bfit.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bfit.app.ui.components.BfitHeader

@Composable
fun InfoScreen(navController: NavHostController) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues())
            .padding(horizontal = 24.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // HEADER BFIT (logo + subtítulo)
        BfitHeader(title = "Información de los Indicadores")

        Spacer(modifier = Modifier.height(24.dp))

        // IMC
        Text(
            text = "IMC (Índice de Masa Corporal)",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Relaciona el peso y la altura para estimar si el peso corporal es adecuado.",
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        //  BFIT 1
        Text(
            text = "BFIT 1",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Evalúa la relación peso-altura considerando la estructura corporal.",
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        //  BFIT 2
        Text(
            text = "BFIT 2",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Considera la circunferencia de cintura para estimar riesgo metabólico.",
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Estos indicadores permiten obtener una visión general del estado de salud.",
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        //  BOTÓN VOLVER
        OutlinedButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
