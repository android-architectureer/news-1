package com.norm.news.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.norm.news.databinding.ListItemArticleBinding
import com.norm.news.domain.NewsArticles
import com.norm.news.network.model.Article
import timber.log.Timber

/**
 * Created by KZYT on 16/01/2020.
 */
class ArticleAdapter(
    private val onClickListener: OnClickListener
) : ListAdapter<NewsArticles, ArticleAdapter.ArticleViewHolder>(ArticleDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        Timber.i("Item size: $itemCount")
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
        holder.bind(item)
    }

    class ArticleViewHolder private constructor(private val binding: ListItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NewsArticles) {
            binding.article = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ArticleViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemArticleBinding.inflate(layoutInflater, parent, false)
                return ArticleViewHolder(binding)
            }
        }
    }

    /**
     * Custom listener that handles clicks on [RecyclerView] items.  Passes the [Article]
     * associated with the current item to the [onClick] function.
     * @param clickListener lambda that will be called with the current [Article]
     */
    class OnClickListener(val clickListener: (article: NewsArticles) -> Unit) {
        fun onClick(article: NewsArticles) = clickListener(article)
    }
}