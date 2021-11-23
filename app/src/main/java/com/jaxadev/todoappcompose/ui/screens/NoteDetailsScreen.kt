package com.jaxadev.todoappcompose.ui.screens

import android.app.Application
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.jaxadev.todoappcompose.database.Note
import com.jaxadev.todoappcompose.database.TodoDatabase
import com.jaxadev.todoappcompose.navigation.Screen

@Composable
fun NoteDetailsScreen(navController: NavController, id: String) {

    val context = LocalContext.current
    val todoDao = TodoDatabase.getInstance(context.applicationContext as Application).todoDao()

    var note = Note(1, "Empty", "Empty")
    val getById = todoDao.getById(id.toInt()).observeAsState().value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(RoundedCornerShape(topStart = 38.dp, topEnd = 38.dp))
                .padding(10.dp)
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp),
                elevation = 2.dp,
                backgroundColor = Color.DarkGray,
            ) {
                Text(
                    text = (getById?.title
                        ?: "Empty Title"),
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    ),
                    fontSize = 20.sp)
            }

            Card(
                modifier = Modifier
                    .padding(horizontal = 5.dp, vertical = 5.dp)
                    .fillMaxWidth(),
                elevation = 2.dp,
                backgroundColor = Color.DarkGray,
            ) {
                Text(
                    text = (getById?.description
                        ?: "Empty Description"),
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    ),
                    fontSize = 18.sp
                )
            }


        }

        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(RoundedCornerShape(topStart = 38.dp, topEnd = 38.dp))
                .padding(10.dp)
        ) {
            FloatingActionButton(
                onClick = {
                    if (getById != null) {
                        note = getById
                    }
                    Log.d("AmaqiHello", "NoteDetailsScreen: $note")
                    navController.navigate(Screen.UpdateScreen.withArgs(Gson().toJson(note)))
                },
                backgroundColor = Color.DarkGray,
                contentColor = Color.White,
                elevation = FloatingActionButtonDefaults.elevation(8.dp)
            ) {
                Icon(Icons.Filled.Edit, "")

            }
        }

    }

}