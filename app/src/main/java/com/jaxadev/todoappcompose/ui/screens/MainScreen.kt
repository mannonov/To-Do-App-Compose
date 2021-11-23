package com.jaxadev.todoappcompose.ui.screens

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jaxadev.todoappcompose.navigation.Screen
import com.jaxadev.todoappcompose.viewmodel.TodoViewModel
import com.jaxadev.todoappcompose.viewmodel.TodoViewModelFactory

@Composable
fun MainScreen(navController: NavController) {

    val context = LocalContext.current
    val mTodoViewModel: TodoViewModel = viewModel(
        factory = TodoViewModelFactory(context.applicationContext as Application)
    )

    val notes = mTodoViewModel.readAllData.observeAsState(listOf()).value

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {

        items(
            items = notes,
            itemContent = {
                NoteListItem(note = it, navController = navController)
            }
        )
    }



    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clip(RoundedCornerShape(topStart = 38.dp, topEnd = 38.dp))
            .padding(10.dp)
    ) {
        FloatingActionButton(
            onClick = { navController.navigate(Screen.AddNewNoteScreen.route) },
            backgroundColor = Color.DarkGray,
            contentColor = Color.White,
            elevation = FloatingActionButtonDefaults.elevation(8.dp)
        ) {
            Icon(Icons.Filled.Add, "")

        }
    }

}