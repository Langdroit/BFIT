package com.bfit.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bfit.app.Screen
import com.bfit.app.ui.components.BfitHeader
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import androidx.compose.runtime.LaunchedEffect

@Composable
fun EvaluationScreen(navController: NavHostController) {

    /* ---------- Datos de formulario ---------- */

    val sexos = listOf("Masculino", "Femenino")
    val estructuras = listOf("Pequeña", "Mediana", "Grande")

    var sexo by remember { mutableStateOf(sexos[0]) }
    var edad by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var estructura by remember { mutableStateOf(estructuras[1]) }
    var cintura by remember { mutableStateOf("") }

    /* ---------- Resultados ---------- */

    var imcResultado by remember { mutableStateOf("") }
    var bfit1Resultado by remember { mutableStateOf("") }
    var bfit2Resultado by remember { mutableStateOf("") }
    var estadoGlobal by remember { mutableStateOf("") }
    var colorEstado by remember { mutableStateOf(Color.Gray) }

    var evaluacionCalculada by remember { mutableStateOf(false) }

    /* ---------- Scroll ---------- */

    val scrollState = rememberScrollState()

    // Auto-scroll al calcular
    LaunchedEffect(evaluacionCalculada) {
        if (evaluacionCalculada) {
            scrollState.animateScrollTo(scrollState.maxValue)
        }
    }

    /* ---------- UI ---------- */

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues())
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        /* ---------- HEADER UNIFICADO ---------- */

        BfitHeader(title = "Evaluación de Salud")

        Spacer(modifier = Modifier.height(24.dp))

        /* ---------- FORMULARIO ---------- */

        DropdownCampo("Sexo", sexos, sexo) { sexo = it }
        CampoTexto("Edad (años)", edad) { edad = it }
        CampoTexto("Altura (cm)", altura) { altura = it }
        CampoTexto("Peso (kg)", peso) { peso = it }
        DropdownCampo("Estructura", estructuras, estructura) { estructura = it }
        CampoTexto("Cintura (cm)", cintura) { cintura = it }

        Spacer(modifier = Modifier.height(20.dp))

        /* ---------- BOTÓN CALCULAR ---------- */

        Button(
            onClick = {
                if (altura.isNotBlank() && peso.isNotBlank() && cintura.isNotBlank()) {

                    val alt = altura.toDouble()
                    val pes = peso.toDouble()
                    val cin = cintura.toDouble()

                    val imc = pes / Math.pow(alt / 100, 2.0)
                    val bfit1Salud = (alt / 2) >= pes
                    val bfit2Salud = when (sexo) {
                        "Masculino" -> cin <= 94
                        "Femenino" -> cin <= 80
                        else -> true
                    }

                    imcResultado = "IMC: %.2f".format(imc)
                    bfit1Resultado = "BFIT 1: ${if (bfit1Salud) "Saludable" else "Riesgo"}"
                    bfit2Resultado = "BFIT 2: ${if (bfit2Salud) "Saludable" else "Riesgo"}"

                    val riesgo = listOf(
                        !(imc in 18.5..24.9),
                        !bfit1Salud,
                        !bfit2Salud
                    ).count { it }

                    val (texto, color) = when (riesgo) {
                        0 -> "Óptimo" to Color(0xFF2E7D32)
                        1 -> "Moderado" to Color(0xFFFFA000)
                        2 -> "Alerta" to Color(0xFFFF7043)
                        else -> "Crítico" to Color(0xFFD32F2F)
                    }

                    estadoGlobal = "Estado: $texto"
                    colorEstado = color
                    evaluacionCalculada = true
                }
            }
        ) {
            Text("Calcular")
        }

        /* ---------- RESULTADOS ---------- */

        if (evaluacionCalculada) {

            Spacer(modifier = Modifier.height(24.dp))

            TextoResultado(imcResultado)
            TextoResultado(bfit1Resultado)
            TextoResultado(bfit2Resultado)
            TextoResultado(estadoGlobal, colorEstado)

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val imcEnc = URLEncoder.encode(imcResultado, StandardCharsets.UTF_8.toString())
                    val bfit1Enc = URLEncoder.encode(bfit1Resultado, StandardCharsets.UTF_8.toString())
                    val bfit2Enc = URLEncoder.encode(bfit2Resultado, StandardCharsets.UTF_8.toString())
                    val estadoEnc = URLEncoder.encode(estadoGlobal, StandardCharsets.UTF_8.toString())

                    navController.navigate(
                        "${Screen.Result.route}/$imcEnc/$bfit1Enc/$bfit2Enc/$estadoEnc"
                    )
                }
            ) {
                Text("Avanzar a resultados")
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

/* ---------- COMPONENTES AUXILIARES ---------- */

@Composable
fun CampoTexto(label: String, valor: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = valor,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    )
}

@Composable
fun DropdownCampo(
    label: String,
    opciones: List<String>,
    seleccionado: String,
    onSelect: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(label)

        OutlinedButton(
            onClick = { expanded = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(seleccionado)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            opciones.forEach {
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = {
                        onSelect(it)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun TextoResultado(texto: String, color: Color = Color.Black) {
    Text(
        text = texto,
        fontSize = 16.sp,
        color = color,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}
