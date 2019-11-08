package com.francis.wordcount


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

/**
 * View Model to keep a reference to the word repository and
 * an up-to-date list of all words.
 */

class WordViewModel(wordDao: WordDao) : ViewModel() {

    private val mRepository: WordRepository = WordRepository(wordDao)
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allWords: LiveData<List<Word>>

    init {
        allWords = mRepository.allWords
    }

    fun insert(word: Word) {
        mRepository.insert(word)
    }

    fun update(id: Long) {
        mRepository.update(id)
    }
}
