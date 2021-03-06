package com.jaxadev.todoappcompose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jaxadev.todoappcompose.database.Note
import com.jaxadev.todoappcompose.navigation.Screen

@Composable
fun NoteListItem(note: Note, navController: NavController) {

    Card(
        modifier = Modifier
            .padding(horizontal = 5.dp, vertical = 5.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {

        Row(modifier = Modifier
            .background(Color.DarkGray)
            .clickable {
                navController.navigate(Screen.NoteDetailsScreen.withArgs(note.id.toString()))
            }) {

            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = note.id.toString(),
                    style = MaterialTheme.typography.h5,
                    color = Color.White,
                )
            }

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically)

            ) {
                Text(text = note.title,
                    style = MaterialTheme.typography.h6,
                    color = Color.White,
                    maxLines = 1)
                Text(
                    text = note.description,
                    style = MaterialTheme.typography.caption,
                    maxLines = 1,
                    color = Color.White
                )
            }
        }
    }

}


