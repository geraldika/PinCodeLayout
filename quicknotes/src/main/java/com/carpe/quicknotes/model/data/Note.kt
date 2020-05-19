package com.carpe.quicknotes.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey
    val id: Long,
    val date: Long,
    val title: String,
    val noteText: String
)