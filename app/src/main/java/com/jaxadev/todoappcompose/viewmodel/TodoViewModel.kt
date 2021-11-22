package com.jaxadev.todoappcompose.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.jaxadev.todoappcompose.database.Note
import com.jaxadev.todoappcompose.database.TodoDatabase
import com.jaxadev.todoappcompose.repository.TodoRepository
import com.jaxadev.todoappcompose.ui.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Note>>
    private val repository: TodoRepository

    val callAlertDialog: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    init {
        val todoDao = TodoDatabase.getInstance(application).todoDao()
        repository = TodoRepository(todoDao)
        readAllData = repository.readAllData
    }

    fun addTodo(todoItem: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTodo(todoItem)
        }
    }

    fun updateTodo(todoItem: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTodo(todoItem = todoItem)
        }
    }

    fun deleteTodo(todoItem: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTodo(todoItem = todoItem)
        }
    }

    fun deleteAllTodos() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllTodos()
        }
    }
}

class TodoViewModelFactory(
    private val application: Application,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            return TodoViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
