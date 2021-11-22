package com.jaxadev.todoappcompose.repository

import androidx.lifecycle.LiveData
import com.jaxadev.todoappcompose.database.TodoDatabaseDao
import com.jaxadev.todoappcompose.database.Note

class TodoRepository(private val todoDatabaseDao: TodoDatabaseDao) {

    val readAllData: LiveData<List<Note>> = todoDatabaseDao.getAll()

    suspend fun addTodo(todoItem: Note) {
        todoDatabaseDao.insert(todoItem)
    }

    suspend fun updateTodo(todoItem: Note) {
        todoDatabaseDao.update(todoItem)
    }

    suspend fun deleteTodo(todoItem: Note) {
        todoDatabaseDao.delete(todoItem)
    }

    suspend fun deleteAllTodos() {
        todoDatabaseDao.deleteAllTodos()
    }
}
