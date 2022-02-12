package com.example.noxtton_task.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noxtton_task.R
import com.example.noxtton_task.databinding.RepoItemBinding
import com.example.noxtton_task.listener.Listener
import com.example.noxtton_task.model.GithubRepo
import com.example.noxtton_task.model.Item

class RepositoryAdapter() : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {


    var data: List<Item> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onItemClick: ((Item) -> Unit)? = null

    var onItem: ((Int) -> Boolean)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryAdapter.ViewHolder {
        return ViewHolder(
            RepoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RepositoryAdapter.ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = data.size


    inner class ViewHolder(private val binding: RepoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var currentData: Item
        @SuppressLint("SetTextI18n")
        fun bind() {
            currentData = data[adapterPosition]
            with(binding) {
                root.setOnClickListener {
                    onItemClick?.invoke(currentData)
                }
                if (currentData.description.isNullOrEmpty()) {
                    GithubDescriptionTextView.visibility = View.GONE
                }else{
                    GithubDescriptionTextView.visibility = View.VISIBLE
                }
                if (currentData.existsInRoom!!){
                    GithubItemNameTextView.visibility = View.GONE
                }
                GithubItemSymbolTextView.text = currentData.stargazers_count.toString()
                GithubItemNameTextView.text = currentData.full_name
                GithubDescriptionTextView.text = currentData.description
                GithubLangTextView.text = currentData.language
                GithubUpdateTextView.text =
                    "Updated at " + currentData.updated_at?.dropLast(10)
            }
            onItem?.invoke(currentData.id!!)
        }
    }
}