package com.jaxadev.todoappcompose.ui

import android.app.Application
import android.widget.Toast
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jaxadev.todoappcompose.viewmodel.TodoViewModel
import com.jaxadev.todoappcompose.viewmodel.TodoViewModelFactory

@Composable
fun MyAlert() {

    val context = LocalContext.current
    val mTodoViewModel: TodoViewModel = viewModel(
        factory = TodoViewModelFactory(context.applicationContext as Application)
    )

    if (mTodoViewModel.callAlertDialog.observeAsState().value == true) {
        AlertDialog(
            onDismissRequest = { mTodoViewModel.callAlertDialog.value = false },
            title = { Text(text = "⚠️Warninig! ", color = Color.Black) },
            text = {
                Text(text = "Do you agree that all your data will be deleted?",
                    color = Color.Black)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        mTodoViewModel.deleteAllTodos()
                        mTodoViewModel.callAlertDialog.value = false
                    }) {
                    Text(text = "Confirm", color = Color.Black)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        mTodoViewModel.callAlertDialog.value = false
                    }) {
                    Text(text = "Dismiss", color = Color.Black)
                }
            },
            backgroundColor = Color.White,
            contentColor = Color.Black
        )
    }
}
