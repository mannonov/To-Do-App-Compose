package com.jaxadev.todoappcompose.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jaxadev.todoappcompose.database.Note

@Dao
interface TodoDatabaseDao {
    @Query("SELECT * from my_todo_list")
    fun getAll(): LiveData<List<Note>>

    @Query("SELECT * from my_todo_list where id = :id")
    fun getById(id: Int): Note?

    @Insert
    suspend fun insert(item: Note)

    @Update
    suspend fun update(item: Note)

    @Delete
    suspend fun delete(item: Note)

    @Query("DELETE FROM my_todo_list")
    suspend fun deleteAllTodos()
}
