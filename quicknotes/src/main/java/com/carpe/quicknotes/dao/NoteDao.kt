package com.carpe.quicknotes.dao

import androidx.room.*
import com.carpe.quicknotes.model.data.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM Note")
    fun getAll(): List<Note?>?

    @Query("SELECT * FROM Note WHERE id = :id")
    fun getById(id: Long): Note?

    @Insert
    fun insert(employee: Note?)

    @Update
    fun update(employee: Note?)

    @Delete
    fun delete(employee: Note?)
}