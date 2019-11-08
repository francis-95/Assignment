package com.francis.wordcount


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_item.view.*


class WordListAdapter(private val clickListener: WordListener) :
    RecyclerView.Adapter<WordListAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent,
            false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val word = mWords?.get(position)
        val noWord = Word("No Word")
        val current = word ?: noWord
        holder.bind(clickListener, current)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return mWords?.size ?: 0
    }

    //the class is holding the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(btnClickListener: WordListener, item: Word) {

            itemView.textView.text = item.word
            itemView.btn.text = item.clickCount.toString()
            itemView.btn.setOnClickListener { btnClickListener.onClick(item.id) }
        }
    }

    private var mWords: List<Word>? = null // Cached copy of words

    fun setWords(words: List<Word>) {
        mWords = words
        notifyDataSetChanged()
    }

    class WordListener(val clickListener: (wordId: Long) -> Unit) {
        fun onClick(wordId: Long) = clickListener(wordId)
    }
}