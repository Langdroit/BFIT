# BFIT (Aplicación Android)

**BFIT** es una aplicación móvil de salud y bienestar diseñada para acompañar a las personas en el control y mantenimiento de un peso saludable. El proyecto ha avanzado incorporando mejoras en la arquitectura, validaciones y separación de la lógica de cálculo.

## Objetivo
En la **Semana 1** se desarrolla la base del MVP:
- Entrada y validación de datos (sexo, edad, altura, estructura, peso, cintura).
- Cálculo de los indicadores **IMC**, **BFIT 1** y **BFIT 2**.
- Determinación del estado global (verde, amarillo, naranja o rojo).

## Actualizaciones del Proyecto
- Incorporación de una **capa domain** con funciones puras para el cálculo de IMC, BFIT 1, BFIT 2 y el estado global.
- Creación de un **ViewModel** con arquitectura MVVM para gestionar el estado del formulario, las validaciones y el resultado final.
- Implementación de **StateFlow** para manejar de forma reactiva los datos ingresados, errores y resultados.
- Actualización de la pantalla construida con Jetpack Compose para integrarse con el ViewModel y mostrar resultados dinámicamente.
- Adición de validaciones y mensajes de error para mejorar la experiencia del usuario.
- Reorganización completa del proyecto en tres capas: UI, ViewModel y Domain.

## Actualización Final – Iteración de interfaz y experiencia de usuario
  Durante esta iteración se incorporaron mejoras significativas en la experiencia de usuario y en la coherencia visual de la aplicación:
- Unificación del encabezado visual (**logo BFIT + subtítulo**) en todas las pantallas principales para mantener consistencia de identidad.
- Reorganización del layout en las pantallas de Resultados, Resumen e Información, mejorando la jerarquía visual y la legibilidad de los datos.
- Implementación de navegación más clara, incorporando botones de **volver** e **inicio** según el contexto de uso.
- Mejora en el flujo de la pantalla de evaluación, asegurando que los resultados calculados sean visibles mediante desplazamiento automático.
- Ajustes estéticos generales (espaciados, alineación y tamaños de texto) siguiendo principios de diseño simple y minimalista.
  Estas mejoras consolidan la aplicación como un MVP funcional, claro y consistente desde el punto de vista visual y de navegación.

## Tecnología
- Android Studio Baklava (Kotlin, Jetpack Compose, Material 3)
- Arquitectura **MVVM** (UI + ViewModel + Domain)
- Min SDK 24+
- Control de versiones con GitHub

## Ejecución
1. Abrir el proyecto en Android Studio.
2. Compilar con **Build ▸ Make Project**.
3. Ejecutar con **Run** en el emulador.
