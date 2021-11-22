package com.jaxadev.todoappcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.jaxadev.todoappcompose.ui.AddNewNoteScreen
import com.jaxadev.todoappcompose.ui.MainScreen
import com.jaxadev.todoappcompose.ui.Screen

@Composable
fun Navigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {

            MainScreen(navController = navController)

        }
        composable(route = Screen.AddNewNoteScreen.route + "/{id}",
            arguments = listOf(
                navArgument("note") {
                    type = NavType.StringType
                    defaultValue = "Empty Note"
                    nullable = true
                }
            )) {

            AddNewNoteScreen(navController = navController)

        }

    }


}


