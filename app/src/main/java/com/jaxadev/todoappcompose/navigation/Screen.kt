package com.jaxadev.todoappcompose.navigation

sealed class Screen(val route: String){

    object MainScreen : Screen("main_screen")
    object AddNewNoteScreen : Screen("add_new_note_screen")
    object NoteDetailsScreen: Screen("note_details")

    fun withArgs(vararg args:String): String{

        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }


    }

}
