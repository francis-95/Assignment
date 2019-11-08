package com.francis.wordcount

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WordDao {

    // LiveData is a data holder class that can be observed within a given lifecycle.
    // Always holds/caches latest version of data. Notifies its active observers when the
    // data has changed. Since we are getting all the contents of the database,
    // we are notified whenever any of the database contents have changed.
    @get:Query("SELECT * from word_table ORDER BY word ASC")
    val alphabetizedWords: LiveData<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(word: Word)

    @Insert
    fun insertAll(words: List<Word>)

    @Query("UPDATE word_table SET clickCount = clickCount + 1 WHERE id = :id")
    fun updateCount(id: Long): Int

    @Query("DELETE FROM word_table")
    fun deleteAll()
}
