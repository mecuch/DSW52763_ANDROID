package com.example.dsw_52763_android.view

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dsw_52763_android.R
import com.example.dsw_52763_android.model.ToDo
import com.example.dsw_52763_android.utils.clickables
import com.example.dsw_52763_android.utils.colors
import com.example.dsw_52763_android.utils.images
import com.example.dsw_52763_android.utils.non_clickables
import com.example.dsw_52763_android.view_model.ToDoViewModel
import java.util.Locale

@Composable
fun NotesPage(viewModel: ToDoViewModel, navController: NavController) {

    val todoList by viewModel.allNotes.observeAsState()
    var inputText by remember { mutableStateOf("") }
    val email = com.google.firebase.auth.FirebaseAuth.getInstance().currentUser?.email ?: "user"

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues()),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(vertical = 22.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                clickables.ClickableBack(navController, "homePage/{userId}")
                non_clickables.UserLoginText(email)
                clickables.ClickableLogout(navController)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                non_clickables.MediumHeaderText("Notes/To Do List\nManager")
                images.Logos()
            }
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
            ) {
                non_clickables.StandardTextField(
                    values = inputText,
                    onValueChange = { inputText = it },
                    labelValues = "New note",
                    verticalSize = 265
                )
                Spacer(modifier = Modifier.width(15.dp))
                clickables.FuncClickableButton({
                    viewModel.addNote(inputText)
                    inputText = ""
                }, "Add note")
            }
        }

        if (todoList.isNullOrEmpty()) {
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    text = "No items yet",
                    fontSize = 16.sp
                )
            }
        } else {
            itemsIndexed(todoList!!) { _: Int, item: ToDo ->
                TodoItem(item = item, onDelete = {
                    viewModel.deleteNote(item.id)
                })
            }
        }
    }
}

@Composable
fun TodoItem(item : ToDo, onDelete : ()-> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(3.dp))
            .background(colors.ornamentscolor)
            .padding(16.dp)
            .height(265.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = SimpleDateFormat("HH:mm:aa, dd/mm", Locale.ENGLISH).format(item.createdAt),
                fontSize = 15.sp,
                color = colors.grantedcolor
            )
            Text(
                text = item.title,
                fontSize = 25.sp,
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
