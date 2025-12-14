package com.bfit.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.bfit.app.ui.AppNavGraph
import com.bfit.app.ui.components.BfitLightSweepBackground
import com.bfit.app.ui.theme.BFITTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BFITTheme {
                val navController = rememberNavController()

                BfitLightSweepBackground {
                    AppNavGraph(navController)
                }
            }
        }
    }
}
