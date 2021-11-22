package com.jaxadev.todoappcompose.ui

import android.app.Application
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jaxadev.todoappcompose.database.Note
import com.jaxadev.todoappcompose.viewmodel.TodoViewModel
import com.jaxadev.todoappcompose.viewmodel.TodoViewModelFactory

@Composable
fun AddNewNoteScreen(navController: NavController) {

    var textTitle by remember { mutableStateOf("") }
    var textDescription by remember { mutableStateOf("") }

    val context = LocalContext.current
    val mTodoViewModel: TodoViewModel = viewModel(
        factory = TodoViewModelFactory(context.applicationContext as Application)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.60f)
                .clip(RoundedCornerShape(topStart = 38.dp, topEnd = 38.dp))
                .padding(10.dp)
        ) {

            Text(
                text = "Note",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp,
                    color = Color.White,
                ),
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.padding(20.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    value = textTitle,
                    onValueChange = { textTitle = it },
                    label = { Text(text = "Text", color = Color.White) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(0.8f),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.DarkGray,
                        cursorColor = Color.White),
                    textStyle = TextStyle(Color.White)
                )
                OutlinedTextField(
                    value = textDescription,
                    onValueChange = { textDescription = it },
                    label = { Text("Description", color = Color.White) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.DarkGray,
                        cursorColor = Color.White),
                    textStyle = TextStyle(Color.White)
                )

            }
            Spacer(modifier = Modifier.padding(20.dp))
            Button(
                onClick = {
                    insertTodoInDB(textTitle, textDescription, mTodoViewModel)
                    navController.navigate(Screen.MainScreen.route)
                },
                contentPadding = PaddingValues(
                    start = 20.dp,
                    top = 12.dp,
                    end = 20.dp,
                    bottom = 12.dp
                ), colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.DarkGray,
                    contentColor = Color.White)
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Add Note",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Add Note", color = Color.White)
            }

        }

    }
}

private fun insertTodoInDB(title: String, description: String, mTodoViewModel: TodoViewModel) {
    val note = Note(title = title, description = description)
    mTodoViewModel.addTodo(note)
}