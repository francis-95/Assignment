package com.francis.wordcount

import android.os.AsyncTask.execute
import androidx.lifecycle.LiveData

internal class WordRepository(private val wordDao: WordDao) {

    val allWords: LiveData<List<Word>> = wordDao.alphabetizedWords

    // You must call this on a non-UI thread or your app will crash.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.

    fun insert(word: Word) {
        execute{ wordDao.insert(word) }
    }

    fun update(id: Long) {
        execute { wordDao.updateCount(id) }
    }

}
