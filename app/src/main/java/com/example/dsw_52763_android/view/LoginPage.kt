package com.example.dsw_52763_android.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dsw_52763_android.utils.clickables
import com.example.dsw_52763_android.utils.images
import com.example.dsw_52763_android.utils.non_clickables
import com.example.dsw_52763_android.utils.routes
import com.example.dsw_52763_android.view_model.AuthState
import com.example.dsw_52763_android.view_model.AuthViewModel
import com.google.firebase.auth.FirebaseAuth


@Composable
fun LoginPage(navController: NavController, authViewModel : AuthViewModel){
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val authState by authViewModel.authState.observeAsState()
    var showValidationError by remember { mutableStateOf(false) }

    LaunchedEffect(authState) {
        if (authState is AuthState.Authenticated) {
            val userId = FirebaseAuth.getInstance().currentUser?.uid
            if (userId != null) {
                navController.navigate("homePage/$userId") {
                    popUpTo(0) { inclusive = true }

                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        images.Logos()
        Column{
            non_clickables.HeaderText("Sign In")
            Spacer(modifier = Modifier.height(20.dp))
            non_clickables.StandardTextField(
                values = username,
                labelValues = "User name/E-mail",
                onValueChange = { username = it },
                verticalSize = 65

            )
            Spacer(modifier = Modifier.height(15.dp))
            non_clickables.StandardPasswordTextField(
                values = password,
                labelValues = "Password",
                onValueChange = { password = it }
            )
            Spacer(modifier = Modifier.height(15.dp))
            clickables.FuncClickableButton({
                if (username.isNotEmpty() && password.isNotEmpty()) {
                    authViewModel.login(username, password)
                } else {
                    showValidationError = true
                }
            }, "Login")

            if (authState is AuthState.Error) {
                val error = (authState as AuthState.Error).message
                non_clickables.WarningErrorText(error)
            }

            if (showValidationError) {
                non_clickables.WarningErrorText("Login error!")
            }
            Spacer(modifier = Modifier.height(90.dp))
            clickables.ClickableText(navController,
                "Don't have an account?",
                "Sign Up",
                routes.registerPage
            )
        }
    }

}


