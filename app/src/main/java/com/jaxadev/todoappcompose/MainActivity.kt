package com.jaxadev.todoappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jaxadev.todoappcompose.navigation.Navigation
import com.jaxadev.todoappcompose.ui.MyAlert
import com.jaxadev.todoappcompose.ui.MyApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
            MyAlert()
        }
    }
}



