package com.jaxadev.todoappcompose.ui

import android.app.Application
import android.widget.Toast
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jaxadev.todoappcompose.navigation.Navigation
import com.jaxadev.todoappcompose.viewmodel.TodoViewModel
import com.jaxadev.todoappcompose.viewmodel.TodoViewModelFactory

@Composable
fun MyApp() {

    val context = LocalContext.current
    val mTodoViewModel: TodoViewModel = viewModel(
        factory = TodoViewModelFactory(context.applicationContext as Application)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "TO-DO Compose") },
                actions = {
                    IconButton(onClick = {
                        mTodoViewModel.callAlertDialog.value = true
                    }) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "Delete All"
                        )
                    }
                },
                backgroundColor = Color.DarkGray,
                contentColor = Color.White

            )
        },
        content = {

            Navigation()

        }
    )
}