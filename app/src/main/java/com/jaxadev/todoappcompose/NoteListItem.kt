package com.jaxadev.todoappcompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.jaxadev.todoappcompose.model.Note

@Composable
fun NoteListItem(note: Note) {

    Row() {

        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = note.id,style = typography.h5,)
        }

        Column() {
            Text(text = note.title, style = typography.h6)
            Text(text = note.description, style = typography.caption, maxLines = 1)
        }
    }

}