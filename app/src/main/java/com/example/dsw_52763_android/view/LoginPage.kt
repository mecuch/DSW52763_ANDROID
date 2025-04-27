package com.example.dsw_52763_android.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dsw_52763_android.R
import com.example.dsw_52763_android.utils.clickables
import com.example.dsw_52763_android.utils.images
import com.example.dsw_52763_android.utils.non_clickables
import com.example.dsw_52763_android.utils.routes
import com.example.dsw_52763_android.view_model.LoginUserViewModel



@Composable
fun LoginPage(navController: NavController, loginViewModel : LoginUserViewModel){
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val loginViewModel: LoginUserViewModel = viewModel()
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
                onValueChange = { username = it }
            )
            Spacer(modifier = Modifier.height(15.dp))
            non_clickables.StandardPasswordTextField(
                values = password,
                labelValues = "Password",
                onValueChange = { password = it }
            )
            Spacer(modifier = Modifier.height(15.dp))
            clickables.LoginClickableButton(navController, username, password, loginViewModel)
            Spacer(modifier = Modifier.height(90.dp))
            clickables.ClickableText(navController,
                "Don't have an account?",
                "Sign Up",
                routes.registerPage
            )
        }
    }

}


