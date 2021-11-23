package com.jaxadev.todoappcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.jaxadev.todoappcompose.ui.screens.AddNewNoteScreen
import com.jaxadev.todoappcompose.ui.screens.MainScreen
import com.jaxadev.todoappcompose.ui.screens.NoteDetailsScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {

            MainScreen(navController = navController)

        }
        composable(route = Screen.AddNewNoteScreen.route) {

            AddNewNoteScreen(navController = navController)

        }
        composable(route = Screen.NoteDetailsScreen.route + "/{id}", arguments = listOf(navArgument("id") {
            type = NavType.StringType
            defaultValue = "1"
            nullable = true
        })) {

            NoteDetailsScreen(navController = navController, id = it.arguments?.getString("id"))


        }

    }


}


