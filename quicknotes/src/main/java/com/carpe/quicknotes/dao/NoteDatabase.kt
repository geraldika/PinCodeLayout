package com.carpe.quicknotes.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.carpe.quicknotes.model.data.Note


@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}