package com.jaxadev.todoappcompose.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.jaxadev.todoappcompose.NoteListItem
import com.jaxadev.todoappcompose.NoteProvider
import com.jaxadev.todoappcompose.Screen

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
            )) { noteEntry ->

            AddNewNoteScreen(note = noteEntry.arguments?.getString("note"))

        }

    }


}

@Composable
fun MainScreen(navController: NavController) {

    val notes = remember {
        NoteProvider.noteList
    }

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

@Composable
fun AddNewNoteScreen(note: String?) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.60f)
                .clip(RoundedCornerShape(topStart = 38.dp, topEnd = 38.dp))
                .padding(10.dp)
        ) {

            Text(
                text = "Note",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                ),
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.padding(20.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    value = "Title",
                    onValueChange = { var text = it },
                    label = { Text("Label") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f),
                )
                OutlinedTextField(
                    value = "Description",
                    onValueChange = { var text = it },
                    label = { Text("Description") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f),
                )

            }

        }

    }
}

