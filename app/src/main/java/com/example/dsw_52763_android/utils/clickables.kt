package com.example.dsw_52763_android.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object clickables {
    @Composable
    fun ClickableBack(navController: NavController) {
        Row {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Wstecz",
            )
            Text("back",
                fontSize = 18.sp,
                color = colors.headerfontcolor,
                modifier = Modifier.clickable { navController.navigate(routes.loginPage) }
            )
        }
    }

    @Composable
    fun ClickableButton(navController: NavController, destinations: String, title: String) {
        Button(onClick = {
            navController.navigate(destinations)
        },
            modifier = Modifier.fillMaxWidth()) {
            Text(title)
        }
    }

    @Composable
    fun ClickableText(navController: NavController, nonclick: String, click: String, destination: String) {
        Row {
            Text(nonclick,
                fontSize = 18.sp,
                color = colors.headerfontcolor)
            Text(click,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = colors.headerfontcolor,
                modifier = Modifier.clickable { navController.navigate(destination) })
        }
    }
}