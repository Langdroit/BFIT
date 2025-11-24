package com.bfit.app.domain

// Se representa el estado global según la cantidad de indicadores en rojo
enum class BfitGlobalColor {
    GREEN,   // 0 rojos
    YELLOW,  // 1 rojo
    ORANGE,  // 2 rojos
    RED      // 3 rojos
}

// Resultado detallado de una evaluación BFIT
data class BfitEvaluationResult(
    val imc: Double,
    val bfit1Reference: Double,
    val isBfit1Red: Boolean,
    val isBfit2Red: Boolean,
    val globalRedCount: Int,
    val globalColor: BfitGlobalColor
)

/**
 * Calcula el IMC a partir de peso (kg) y altura (cm).
 */
fun calcularImc(pesoKg: Double, alturaCm: Double): Double {
    val alturaM = alturaCm / 100.0
    if (alturaM <= 0.0) return 0.0
    return pesoKg / (alturaM * alturaM)
}

/**
 * BFIT 1:
 * bfit1Ref = altura_cm / 2
 * Verde si bfit1Ref <= peso_kg, Rojo si bfit1Ref > peso_kg
 */
fun esBfit1Rojo(pesoKg: Double, alturaCm: Double): Pair<Double, Boolean> {
    val bfit1Ref = alturaCm / 2.0
    val isRed = bfit1Ref > pesoKg
    return bfit1Ref to isRed
}

/**
 * BFIT 2:
 * Hombre: Verde si cintura <= 94, Rojo si > 94
 * Mujer:  Verde si cintura <= 80, Rojo si > 80
 */
fun esBfit2Rojo(sexo: String, cinturaCm: Double): Boolean {
    return when (sexo.uppercase()) {
        "M" -> cinturaCm > 94.0
        "F" -> cinturaCm > 80.0
        else -> false
    }
}

/**
 * Determina el color global según cantidad de "rojos":
 * 0 rojos = Verde
 * 1 rojo  = Amarillo
 * 2 rojos = Naranja
 * 3 rojos = Rojo
 */
fun determinarColorGlobal(redCount: Int): BfitGlobalColor {
    return when (redCount) {
        0 -> BfitGlobalColor.GREEN
        1 -> BfitGlobalColor.YELLOW
        2 -> BfitGlobalColor.ORANGE
        else -> BfitGlobalColor.RED
    }
}

/**
 * Función principal que combina todo.
 */
fun evaluarBfit(
    pesoKg: Double,
    alturaCm: Double,
    cinturaCm: Double,
    sexo: String
): BfitEvaluationResult {

    val imc = calcularImc(pesoKg, alturaCm)
    val (bfit1Ref, bfit1Rojo) = esBfit1Rojo(pesoKg, alturaCm)
    val bfit2Rojo = esBfit2Rojo(sexo, cinturaCm)

    val redCount = listOf(
        bfit1Rojo,
        bfit2Rojo
    ).count { it }

    val globalColor = determinarColorGlobal(redCount)

    return BfitEvaluationResult(
        imc = imc,
        bfit1Reference = bfit1Ref,
        isBfit1Red = bfit1Rojo,
        isBfit2Red = bfit2Rojo,
        globalRedCount = redCount,
        globalColor = globalColor
    )
}
