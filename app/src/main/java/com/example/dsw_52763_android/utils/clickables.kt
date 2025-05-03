package com.example.dsw_52763_android.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PowerSettingsNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

object clickables {

//    @Composable
//    fun RegisterClickableButton(
//        navController: NavController,
//        destinations: String,
//        username: String,
//        email: String,
//        password: String,
//        confirmPassword: String,
//        registerViewModel: RegisterUserViewModel // <-- PRZEKAZUJEMY ViewModel
//    ) {
//        Button(
//            onClick = {
//                registerViewModel.registerUser(
//                    fullName = username,
//                    email = email,
//                    password = password,
//                    confirmPassword = confirmPassword
//                )
//            },
//            colors = ButtonDefaults.buttonColors(
//                containerColor = colors.ornamentscolor,
//                contentColor = Color.White
//            ),
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Sign Up")
//        }
//
//        if (registerViewModel.registrationSuccess.value) {
//            LaunchedEffect(Unit) {
//                navController.navigate(destinations) {
//                    popUpTo(destinations) { inclusive = true }
//                }
//            }
//            non_clickables.GrantedText("User created successfully!")
//        }
//
//        if (registerViewModel.errorMessage.value.isNotEmpty()) {
//            non_clickables.WarningErrorText(registerViewModel.errorMessage.value)
//        }
//    }

//    @Composable
//    fun LoginClickableButton(
//        navController: NavController,
//        username: String,
//        password: String,
//        loginViewModel: LoginUserViewModel // <-- PRZEKAZUJEMY ViewModel
//    ) {
//        Button(
//            onClick = {
//                loginViewModel.loginUser(
//                    email = username,
//                    password = password
//                )
//            },
//            colors = ButtonDefaults.buttonColors(
//                containerColor = colors.ornamentscolor,
//                contentColor = Color.White
//            ),
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Sign In")
//        }
//
//        if (loginViewModel.loginSuccess.value) {
//            LaunchedEffect(Unit) {
//                val fullName = loginViewModel.fullName
//                navController.navigate("homePage/$fullName") {
//                    popUpTo(routes.loginPage) { inclusive = true }
//                }
//            }
//            non_clickables.GrantedText("Login Successful!")
//        }
//
//        if (loginViewModel.errorMessage.value.isNotEmpty()) {
//            non_clickables.WarningErrorText(loginViewModel.errorMessage.value)
//        }
//    }
@Composable
fun CustomImageButton(imageResId: Int, navController: NavController, destinations: String, title: String
) {
    Button(
        onClick = { navController.navigate(destinations) },
        contentPadding = PaddingValues(0.dp), // Usuwamy domyślny padding
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent) // Tło przezroczyste
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .matchParentSize()
                    .clip(RoundedCornerShape(12.dp))
            )

            // Overlay z napisem
            Text(
                text = title,
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

    @Composable
    fun ClickableLogout(navController: NavController) {
        Row(
            modifier = Modifier.clickable {
                // Wyloguj użytkownika z Firebase
                FirebaseAuth.getInstance().signOut()

                // Przejdź na stronę logowania
                navController.navigate(routes.loginPage) {
                    popUpTo(0) { inclusive = true }
                }
            }
        ) {
            Text(
                "Logout!",
                fontSize = 14.sp,
                color = colors.headerfontcolor,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Filled.PowerSettingsNew,
                contentDescription = "Wstecz",
                tint = colors.ornamentscolor
            )
        }
    }

    @Composable
    fun ClickableBack(navController: NavController, destination: String) {
        Row {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Wstecz",
                tint = colors.ornamentscolor
            )
            Text("back",
                fontSize = 14.sp,
                color = colors.headerfontcolor,
                modifier = Modifier.clickable { navController.navigate(destination) }
            )
        }
    }

    @Composable
    fun FuncClickableButton(OnClick: () -> Unit, title: String) {
        Button(
            onClick = OnClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.ornamentscolor,
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()) {
            Text(title)
        }
    }

    @Composable
    fun ClickableText(navController: NavController, nonclick: String, click: String, destination: String) {
        Row {
            Text(nonclick,
                fontSize = 15.sp,
                color = colors.headerfontcolor)
            Spacer(modifier = Modifier.width(5.dp))
            Text(click,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = colors.headerfontcolor,
                modifier = Modifier.clickable { navController.navigate(destination) })
        }
    }
}