package com.bfit.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bfit.app.Screen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun EvaluationScreen(navController: NavHostController) {

    val sexos = listOf("Masculino", "Femenino")
    val estructuras = listOf("Pequeña", "Mediana", "Grande")

    var sexo by remember { mutableStateOf(sexos[0]) }
    var edad by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var estructura by remember { mutableStateOf(estructuras[1]) }
    var cintura by remember { mutableStateOf("") }

    var imcResultado by remember { mutableStateOf("") }
    var bfit1Resultado by remember { mutableStateOf("") }
    var bfit2Resultado by remember { mutableStateOf("") }
    var estadoGlobal by remember { mutableStateOf("") }
    var colorEstado by remember { mutableStateOf(Color.Gray) }

    var evaluacionCalculada by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(WindowInsets.systemBars.asPaddingValues())
            .padding(horizontal = 16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "BFIT – Evaluación de Salud",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        DropdownCampo("Sexo", sexos, sexo) { sexo = it }
        CampoTexto("Edad (años)", edad) { edad = it }
        CampoTexto("Altura (cm)", altura) { altura = it }
        CampoTexto("Peso (kg)", peso) { peso = it }
        DropdownCampo("Estructura", estructuras, estructura) { estructura = it }
        CampoTexto("Cintura (cm)", cintura) { cintura = it }

        Spacer(Modifier.height(20.dp))

        Button(onClick = {
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
                bfit1Resultado = "BFIT 1: " + if (bfit1Salud) "Saludable" else "Riesgo"
                bfit2Resultado = "BFIT 2: " + if (bfit2Salud) "Saludable" else "Riesgo"

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
            } else {
                estadoGlobal = "Complete todos los campos"
                colorEstado = Color.Gray
                evaluacionCalculada = false
            }
        }) {
            Text("Calcular")
        }

        Spacer(Modifier.height(24.dp))

        if (evaluacionCalculada) {
            TextoResultado(imcResultado)
            TextoResultado(bfit1Resultado)
            TextoResultado(bfit2Resultado)
            TextoResultado(estadoGlobal, colorEstado)

            Spacer(Modifier.height(24.dp))

            Button(onClick = {
                val imcEnc = URLEncoder.encode(imcResultado, StandardCharsets.UTF_8.toString())
                val bfit1Enc = URLEncoder.encode(bfit1Resultado, StandardCharsets.UTF_8.toString())
                val bfit2Enc = URLEncoder.encode(bfit2Resultado, StandardCharsets.UTF_8.toString())
                val estadoEnc = URLEncoder.encode(estadoGlobal, StandardCharsets.UTF_8.toString())

                navController.navigate(
                    "${Screen.Result.route}/$imcEnc/$bfit1Enc/$bfit2Enc/$estadoEnc"
                )
            }) {
                Text("Avanzar a resultados")
            }
        }
    }
}

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
        Box {
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
}

@Composable
fun TextoResultado(texto: String, color: Color = Color.Black) {
    Text(
        text = texto,
        fontSize = 18.sp,
        color = color,
        modifier = Modifier
            .padding(vertical = 4.dp)
            .background(Color.Transparent)
    )
}
