package com.bfit.app

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Evaluation : Screen("evaluation")
    object Result : Screen("result")
    object History : Screen("history")
    object Info : Screen("info")
}
