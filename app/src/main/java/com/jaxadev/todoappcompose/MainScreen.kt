package com.jaxadev.todoappcompose

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jaxadev.todoappcompose.database.TodoViewModel
import com.jaxadev.todoappcompose.database.TodoViewModelFactory

@Composable
fun MainScreen(navController: NavController) {

    FloatingActionButton(onClick = { navController.navigate(Screen.AddNewNoteScreen.withArgs("Empty")) }) {
        Icon(Icons.Filled.Add, "")
    }

    val context = LocalContext.current
    val mTodoViewModel: TodoViewModel = viewModel(
        factory = TodoViewModelFactory(context.applicationContext as Application)
    )

    val notes = mTodoViewModel.readAllData.observeAsState(listOf()).value


    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier.background(Color.Black)
    ) {

        items(
            items = notes,
            itemContent = {
                NoteListItem(note = it, navController = navController)
            }
        )
    }

}