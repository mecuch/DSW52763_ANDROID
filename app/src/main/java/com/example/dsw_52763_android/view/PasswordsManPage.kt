package com.example.dsw_52763_android.view


import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dsw_52763_android.R
import com.example.dsw_52763_android.model.PassMan
import com.example.dsw_52763_android.utils.clickables
import com.example.dsw_52763_android.utils.colors
import com.example.dsw_52763_android.utils.images
import com.example.dsw_52763_android.utils.non_clickables
import com.example.dsw_52763_android.view_model.PassManViewModel


@Composable
fun PassManPage(viewModel: PassManViewModel, navController: NavController) {

    val recordsList by viewModel.allPasswords.observeAsState()
    var inputTexturls by remember {
        mutableStateOf("")
    }
    var inputTextlogins by remember {
        mutableStateOf("")
    }
    var inputTextpasswords by remember {
        mutableStateOf("")
    }
    val email = com.google.firebase.auth.FirebaseAuth.getInstance().currentUser?.email ?: "user"

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(vertical = 22.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                clickables.ClickableBack(navController, "homePage/{userId}")
                non_clickables.UserLoginText(email)
                clickables.ClickableLogout(navController)

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                non_clickables.MediumHeaderText("Password\nManager")
                images.Logos()
            }
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                non_clickables.StandardTextField(
                    values = inputTexturls,
                    labelValues = "URL",
                    onValueChange = { inputTexturls = it },
                    verticalSize = 65
                )
                Spacer(modifier = Modifier.height(20.dp))
                non_clickables.StandardTextField(
                    values = inputTextlogins,
                    labelValues = "Login",
                    onValueChange = { inputTextlogins = it },
                    verticalSize = 65
                )
                Spacer(modifier = Modifier.height(20.dp))
                non_clickables.StandardTextField(
                    values = inputTextpasswords,
                    labelValues = "Password",
                    onValueChange = { inputTextpasswords = it },
                    verticalSize = 65
                )
                Spacer(modifier = Modifier.height(20.dp))
                clickables.FuncClickableButton({viewModel.addPass(inputTexturls, inputTextlogins, inputTextpasswords)
                    inputTexturls = ""
                    inputTextlogins = ""
                    inputTextpasswords = ""}, "Add record")
            }
        }

        recordsList?.let { list ->
            itemsIndexed(list) { _, item ->
                PassManItem(item = item, onDelete = {
                    viewModel.deletePass(item.id)
                })
            }
        } ?: item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                text = "Brak danych",
                fontSize = 16.sp
            )
        }
        if (recordsList.isNullOrEmpty()) {
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    text = "No records yet",
                    fontSize = 16.sp
                )
            }
        }
    }
}
@Composable
fun PassManItem(item : PassMan, onDelete : ()-> Unit) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(3.dp))
            .background(colors.ornamentscolor)
            .padding(16.dp)
            .height(200.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "URL: ${item.urls}",
                fontSize = 12.sp,
                color = colors.grantedcolor,
                modifier = Modifier.clickable {
                    val rawUrl = item.urls.trim()

                    if (rawUrl.isNotBlank()) {
                        val formattedUrl = if (!rawUrl.startsWith("http://") && !rawUrl.startsWith("https://")) {
                            "https://$rawUrl"
                        } else {
                            rawUrl
                        }
                        val uri = Uri.parse(formattedUrl)
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        context.startActivity(intent)
                    }
                }
            )
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "Login: ${item.logins}",
                fontSize = 18.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "Password: ${item.passwords}",
                fontSize = 18.sp,
                color = Color.White
            )
        }
        IconButton(onClick = onDelete) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_delete_24),
                contentDescription = "Delete",
                tint = Color.White
            )
        }
    }
}