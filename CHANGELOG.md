# Changelog – BFIT

## Versión 1.0 – Semana 1 (MVP Inicial)
- Entrada de datos: sexo, edad, altura, estructura, peso y cintura.
- Cálculo de los indicadores IMC, BFIT 1 y BFIT 2.
- Determinación del estado global (verde, amarillo, naranja o rojo).
- Primera versión de la interfaz desarrollada en Jetpack Compose.

## Versión 1.1 – Separación de Lógica (Domain)
- Creación del archivo BfitCalculations.kt.
- Implementación de funciones puras para IMC, BFIT 1, BFIT 2 y estado global.
- Mejora de mantenibilidad y separación respecto de la UI.

## Versión 1.2 – Implementación de MVVM
- Creación del BfitViewModel.
- Manejo de estado con StateFlow.
- Validación de entradas del usuario (peso, altura, cintura, sexo).
- Exposición del resultado de evaluación y errores mediante flujo de estado.
- Desacoplamiento entre UI y lógica.

## Versión 1.3 – Integración Completa con la UI
- Conexión de Compose con el ViewModel.
- Interfaz reactiva que actualiza los resultados dinámicamente.
- Actualización de campos, mensajes y colores según el estado global.
- Adición de mensajes de error y manejo adecuado de valores inválidos.

## Versión 1.4 – Optimización General
- Reorganización del proyecto en tres capas: UI, ViewModel y Domain.
- Limpieza y ordenamiento del código.
- Ajustes menores en la presentación y flujo de cálculo.
