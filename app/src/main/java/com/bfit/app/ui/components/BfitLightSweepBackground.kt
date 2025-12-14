package com.bfit.app.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BfitLightSweepBackground(
    content: @Composable () -> Unit
) {

    val transition = rememberInfiniteTransition()

    val topOffset by transition.animateFloat(
        initialValue = -300f,
        targetValue = 300f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val bottomOffset by transition.animateFloat(
        initialValue = 300f,
        targetValue = -300f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(modifier = Modifier.fillMaxSize()) {

        // Fondo base
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF9F9F9))
        )

        // Reflejo superior
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .offset(x = topOffset.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color(0xFFCCCCCC),
                            Color.Transparent
                        )
                    )
                )
        )

        // Reflejo inferior
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .align(androidx.compose.ui.Alignment.BottomStart)
                .offset(x = bottomOffset.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color(0xFFCCCCCC),
                            Color.Transparent
                        )
                    )
                )
        )

        // Contenido de la app
        content()
    }
}
