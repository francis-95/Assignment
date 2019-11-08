package com.francis.wordcount

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
data class Word(
           val word: String,
           val clickCount: Int = 0
) {
    @field:PrimaryKey(autoGenerate = true)
    var id: Long = 0
        set(value) {
            field = value
        }
}