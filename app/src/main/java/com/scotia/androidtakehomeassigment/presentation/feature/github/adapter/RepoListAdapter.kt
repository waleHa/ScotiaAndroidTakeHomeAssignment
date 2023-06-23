package com.scotia.androidtakehomeassigment.presentation.feature.github.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.scotia.androidtakehomeassigment.R
import com.scotia.androidtakehomeassigment.databinding.RepoItemBinding
import com.scotia.androidtakehomeassigment.domain.model.GithubRepo
class RepoListAdapter : ListAdapter<GithubRepo, RepoListAdapter.ViewHolder>(DiffCallback()) {

    // inflate the layout with data binding, and return the ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: RepoItemBinding = DataBindingUtil.inflate(inflater, R.layout.repo_item, parent, false)
        return ViewHolder(binding)
    }

    // use the ViewHolder's bind method to bind the current item
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    // ViewHolder now takes a RepoItemBinding argument
    class ViewHolder(private val binding: RepoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        // bind the GithubRepo data to the binding
        fun bind(repo: GithubRepo) {
            binding.repo = repo
            binding.executePendingBindings() // This is important, as it forces the data binding to occur immediately.
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<GithubRepo>() {
        override fun areItemsTheSame(oldItem: GithubRepo, newItem: GithubRepo): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: GithubRepo, newItem: GithubRepo): Boolean =
            oldItem == newItem
    }
}