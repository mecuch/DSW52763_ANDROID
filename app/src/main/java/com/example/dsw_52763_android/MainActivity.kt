package com.example.dsw_52763_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dsw_52763_android.ui.theme.DSW_52763_ANDROIDTheme
import com.example.dsw_52763_android.utils.routes
import com.example.dsw_52763_android.view.LoginPage
import com.example.dsw_52763_android.view.RegisterPage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = routes.loginPage, builder = {
                composable(routes.loginPage){
                    LoginPage(navController = navController)
                }
                composable(routes.registerPage){
                    RegisterPage(navController = navController)
                }
            })

        }
    }
}

