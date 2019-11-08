package com.francis.wordcount

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_new_word.*

/**
 * Activity for entering a word.
 */

class NewWordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)

        button_save.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(edit_word!!.text)) {
                setResult(RESULT_CANCELED, replyIntent)
            } else {
                val word = edit_word!!.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {

        const val EXTRA_REPLY = "com.francis.wordcount.wordlistsql.REPLY"
    }
}

