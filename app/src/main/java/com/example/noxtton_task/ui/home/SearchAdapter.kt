package com.example.noxtton_task.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noxtton_task.databinding.RepoItemBinding
import com.example.noxtton_task.databinding.SearchItemBinding
import com.example.noxtton_task.model.Item
import com.example.noxtton_task.model.repo.RepositorySearch


class SearchAdapter() : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {


    var data: List<RepositorySearch> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onItemClick: ((String) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchAdapter.ViewHolder {
        return ViewHolder(
            SearchItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchAdapter.ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = data.size


    inner class ViewHolder(private val binding: SearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var currentData: RepositorySearch

        @SuppressLint("SetTextI18n")
        fun bind() {
            currentData = data[adapterPosition]
            with(binding) {
                root.setOnClickListener {
                    onItemClick?.invoke(currentData.searchName!!)
                }
                GithubItemNameTextView.text = currentData.searchName
            }
        }
    }
}
