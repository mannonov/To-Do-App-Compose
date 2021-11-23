package com.jaxadev.todoappcompose.ui.screens

import android.app.Application
import android.os.Handler
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
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
import com.jaxadev.todoappcompose.database.TodoDatabase
import com.jaxadev.todoappcompose.navigation.Screen
import com.jaxadev.todoappcompose.viewmodel.TodoViewModel
import com.jaxadev.todoappcompose.viewmodel.TodoViewModelFactory

@Composable
fun UpdateScreen(navController: NavController, note: Note) {

    val context = LocalContext.current
    val mTodoViewModel: TodoViewModel = viewModel(
        factory = TodoViewModelFactory(context.applicationContext as Application)
    )

    var textTitle by remember {
        mutableStateOf(note.title)
    }
    var textDescription by remember {
        mutableStateOf(note.description)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
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
                    onValueChange = {
                        textTitle = it
                    },
                    label = { Text(text = "Title", color = Color.White) },
                    singleLine = false,
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
                    singleLine = false,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .fillMaxHeight(0.5f),
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
                    mTodoViewModel.updateTodo(Note(id = note.id,
                        title = textTitle,
                        description = textDescription))
                    navController.navigate(Screen.NoteDetailsScreen.withArgs(note.id.toString()))
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
                    Icons.Filled.Build,
                    contentDescription = "Update Note",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Update Note", color = Color.White)
            }

        }

    }
}