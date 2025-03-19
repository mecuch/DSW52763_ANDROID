package com.example.dsw_52763_android.utils

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

object clickables {
    @Composable
    fun ClickableBack(navController: NavController) {
        Text("<back",
            modifier = Modifier.clickable {navController.navigate(routes.loginPage)}
        )
    }
}