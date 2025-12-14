package com.bfit.app.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.bfit.app.ui.theme.BfitPink

@Composable
fun BfitLogo(
    modifier: Modifier = Modifier,
    fontSize: Int = 32
) {
    Text(
        text = "B  F  I  T",
        modifier = modifier.padding(vertical = 16.dp),
        color = BfitPink,
        fontSize = fontSize.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.SansSerif,
        textAlign = TextAlign.Center,
        letterSpacing = 4.sp
    )
}
