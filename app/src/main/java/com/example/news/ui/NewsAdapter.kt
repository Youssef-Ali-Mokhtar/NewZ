package com.example.news.uiimport

import com.example.news.R
import com.example.news.model.news.ArticlesItem
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_news_item.view.*
import java.util.*

class NewsAdapter(val listener: (View, ArticlesItem, Int) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var data: List<ArticlesItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_news_item, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) =
        holder.bind(data[position])

    fun swapData(data: List<ArticlesItem>) {
        this.data = data
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ArticlesItem) = with(itemView) {
            titleTextView.text = item.title
            Glide.with(itemView).load(item.urlToImage).into(imageView)
            setOnClickListener {
                listener.invoke(it, item, adapterPosition)
            }
        }
    }
}