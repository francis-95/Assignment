package com.francis.wordcount


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var wordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val application = this.application

        val wordDao = AppDatabase.getInstance(application).wordDao()

        val wordViewModelFactory = WordViewModelFactory(wordDao)

        wordViewModel = ViewModelProviders.of(
            this, wordViewModelFactory).get(WordViewModel::class.java)

        val adapter = WordListAdapter(WordListAdapter.WordListener {
            wordViewModel.update(it)
        })

        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)

        wordViewModel.allWords.observe(this,
            Observer<List<Word>> { words ->
                // Update the cached copy of the words in the adapter.
                adapter.setWords(words)
            })

        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewWordActivity::class.java)
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            val word = data?.getStringExtra(NewWordActivity.EXTRA_REPLY)?.let { Word(it) }
            if (word != null) {
                wordViewModel.insert(word)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }

    companion object {

        const val NEW_WORD_ACTIVITY_REQUEST_CODE = 1
    }
}
