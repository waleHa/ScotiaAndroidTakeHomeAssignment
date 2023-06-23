package com.scotia.androidtakehomeassigment.presentation.feature.github.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.scotia.androidtakehomeassigment.R
import com.scotia.androidtakehomeassigment.databinding.RepoItemBinding
import com.scotia.androidtakehomeassigment.domain.model.GithubRepo

class RepoListAdapter(private val onRepoClicked: (GithubRepo) -> Unit) : ListAdapter<GithubRepo, RepoListAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: RepoItemBinding = DataBindingUtil.inflate(inflater, R.layout.repo_item, parent, false)
        return ViewHolder(binding, onRepoClicked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: RepoItemBinding, private val onRepoClicked: (GithubRepo) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: GithubRepo) {
            binding.repo = repo
            binding.root.setOnClickListener { onRepoClicked(repo) }
            binding.executePendingBindings()
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<GithubRepo>() {
        override fun areItemsTheSame(oldItem: GithubRepo, newItem: GithubRepo): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: GithubRepo, newItem: GithubRepo): Boolean =
            oldItem == newItem
    }
}
