package com.example.dsw_52763_android.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dsw_52763_android.utils.clickables
import com.example.dsw_52763_android.utils.non_clickables
import com.example.dsw_52763_android.utils.routes
import com.example.dsw_52763_android.view_model.AuthState
import com.example.dsw_52763_android.view_model.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.material3.Button

@Composable
fun RegisterPage(navController: NavController, authViewModel : AuthViewModel){
    var username by remember { mutableStateOf("") }
    var emails by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmpassword by remember { mutableStateOf("") }
    val authState by authViewModel.authState.observeAsState()
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
            .padding(WindowInsets.safeDrawing.asPaddingValues())
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            clickables.ClickableBack(navController, routes.loginPage)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column {
            non_clickables.HeaderText("Sign Up")
            Spacer(modifier = Modifier.height(20.dp))
            non_clickables.StandardTextField(
                values = username,
                labelValues = "Full name",
                onValueChange = { username = it }
            )
            Spacer(modifier = Modifier.height(15.dp))
            non_clickables.StandardTextField(
                values = emails,
                labelValues = "E-mail",
                onValueChange = { emails = it }
            )
            Spacer(modifier = Modifier.height(15.dp))
            non_clickables.StandardPasswordTextField(
                values = password,
                labelValues = "Password",
                onValueChange = { password = it }
            )
            Spacer(modifier = Modifier.height(15.dp))
            non_clickables.StandardPasswordTextField(
                values = confirmpassword,
                labelValues = "Confirm Password",
                onValueChange = { confirmpassword = it }
            )
            Spacer(modifier = Modifier.height(15.dp))
            Button(
                onClick = {
                    if (password == confirmpassword) {
                        authViewModel.signup(emails, password)
                    } else {
                        // Możesz zareagować lokalnie np. komunikatem
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Sign Up")
            }

            if (authState is AuthState.Error) {
                val error = (authState as AuthState.Error).message
                non_clickables.WarningErrorText(error)
            }
            Spacer(
                modifier = Modifier.height(100.dp))
            clickables.ClickableText(navController,
                "Already have a account?",
                "Sign In",
                routes.loginPage)
        }
    }
}