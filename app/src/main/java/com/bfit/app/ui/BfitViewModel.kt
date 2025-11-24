package com.bfit.app.ui

import androidx.lifecycle.ViewModel
import com.bfit.app.domain.BfitEvaluationResult
import com.bfit.app.domain.BfitGlobalColor
import com.bfit.app.domain.evaluarBfit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BfitViewModel : ViewModel() {

    //  Variables de entrada (el usuario las completa en pantalla)
    private val _pesoKg = MutableStateFlow("")
    val pesoKg: StateFlow<String> = _pesoKg.asStateFlow()

    private val _alturaCm = MutableStateFlow("")
    val alturaCm: StateFlow<String> = _alturaCm.asStateFlow()

    private val _cinturaCm = MutableStateFlow("")
    val cinturaCm: StateFlow<String> = _cinturaCm.asStateFlow()

    private val _sexo = MutableStateFlow("M") // Valor por defecto: masculino
    val sexo: StateFlow<String> = _sexo.asStateFlow()

    // Resultado de la evaluación BFIT
    private val _resultado = MutableStateFlow<BfitEvaluationResult?>(null)
    val resultado: StateFlow<BfitEvaluationResult?> = _resultado.asStateFlow()

    // Errores simples de validación
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    // Mensaje de estado global (verde/amarillo/naranja/rojo)
    private val _statusMessage = MutableStateFlow("")
    val statusMessage: StateFlow<String> = _statusMessage.asStateFlow()

    // Setters para actualizar valores de entrada
    fun onPesoChanged(value: String) {
        _pesoKg.value = value
        _error.value = null
    }

    fun onAlturaChanged(value: String) {
        _alturaCm.value = value
        _error.value = null
    }

    fun onCinturaChanged(value: String) {
        _cinturaCm.value = value
        _error.value = null
    }

    fun onSexoChanged(value: String) {
        _sexo.value = value.uppercase()
        _error.value = null
    }

    // Lógica principal: CALCULAR BFIT
    fun calcular() {
        _error.value = null
        _statusMessage.value = ""

        val peso = _pesoKg.value.toDoubleOrNull()
        val altura = _alturaCm.value.toDoubleOrNull()
        val cintura = _cinturaCm.value.toDoubleOrNull()
        val sexoInput = _sexo.value

        // Validaciones simples
        if (peso == null || peso <= 0) {
            _error.value = "Ingrese un peso válido."
            _resultado.value = null
            return
        }

        if (altura == null || altura <= 0) {
            _error.value = "Ingrese una altura válida."
            _resultado.value = null
            return
        }

        if (cintura == null || cintura <= 0) {
            _error.value = "Ingrese una medida de cintura válida."
            _resultado.value = null
            return
        }

        if (sexoInput.isBlank()) {
            _error.value = "Ingrese el sexo (M/F)."
            _resultado.value = null
            return
        }

        // Se llama al motor de cálculo en la capa domain
        val resultadoEvaluacion = evaluarBfit(
            pesoKg = peso,
            alturaCm = altura,
            cinturaCm = cintura,
            sexo = sexoInput
        )

        _resultado.value = resultadoEvaluacion

        _statusMessage.value = when (resultadoEvaluacion.globalColor) {
            BfitGlobalColor.GREEN ->
                "Estado saludable (sin indicadores en rojo)."
            BfitGlobalColor.YELLOW ->
                "Alerta leve: 1 indicador en rojo."
            BfitGlobalColor.ORANGE ->
                "Alerta moderada: 2 indicadores en rojo."
            BfitGlobalColor.RED ->
                "Alerta grave: 3 indicadores en rojo."
        }
    }
}

