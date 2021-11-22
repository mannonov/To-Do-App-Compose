package com.jaxadev.todoappcompose

sealed class Screen(val route: String){

    object MainScreen : Screen("main_screen")
    object AddNewNoteScreen : Screen("add_new_note_screen")

    fun withArgs(vararg args:String): String{

        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }


    }

}
