package com.jaxadev.todoappcompose

import android.util.Log
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@Composable
fun ToDoSnackBar(message: String) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier,
        scaffoldState = scaffoldState
    ) {
        Button(
            onClick = {
                coroutineScope.launch {
                    val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                        message = message,
                        actionLabel = "Do something."
                    )
                    when (snackbarResult) {
                        SnackbarResult.Dismissed -> Log.d("SnackbarDemo", "Dismissed")
                        SnackbarResult.ActionPerformed -> Log.d(
                            "SnackbarDemo",
                            "Snackbar's button clicked"
                        )
                    }
                }
            }
        ) {
            Text(text = "A button that shows a Snackbar")
        }
    }
}