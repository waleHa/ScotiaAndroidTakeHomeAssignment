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
/**
 * Adapter for displaying a list of GitHub repositories.
 *
 * @param onRepoClicked callback function to handle repository item click events
 */
class RepoListAdapter(private val onRepoClicked: (GithubRepo) -> Unit) :
    ListAdapter<GithubRepo, RepoListAdapter.ViewHolder>(DiffCallback()) {
    /**
     * Creates a ViewHolder for the adapter.
     *
     * @param parent the parent ViewGroup
     * @param viewType the type of the view
     * @return the created ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: RepoItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.repo_item, parent, false)
        return ViewHolder(binding, onRepoClicked)
    }

    /**
     * Binds data to the ViewHolder at the specified position.
     *
     * @param holder the ViewHolder to bind data to
     * @param position the position of the item in the list
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    /**
     * ViewHolder class for displaying a single repository item.
     *
     * @param binding the ViewDataBinding for the item layout
     * @param onRepoClicked callback function to handle repository item click events
     */
    class ViewHolder(
        private val binding: RepoItemBinding,
        private val onRepoClicked: (GithubRepo) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        /**
         * Binds the repository data to the ViewHolder.
         *
         * @param repo the [GithubRepo] object representing the repository
         */
        fun bind(repo: GithubRepo) {
            binding.repo = repo
            binding.root.setOnClickListener { onRepoClicked(repo) }
            binding.executePendingBindings()
        }
    }

    /**
     * DiffCallback for calculating the difference between two lists of repositories.
     */
    class DiffCallback : DiffUtil.ItemCallback<GithubRepo>() {
        /**
         * Checks if two items represent the same object.
         *
         * @param oldItem the old item
         * @param newItem the new item
         * @return true if the items represent the same object, false otherwise
         */
        override fun areItemsTheSame(oldItem: GithubRepo, newItem: GithubRepo): Boolean =
            oldItem.name == newItem.name

        /**
         * Checks if the contents of two items are the same.
         *
         * @param oldItem the old item
         * @param newItem the new item
         * @return true if the contents of the items are the same, false otherwise
         */
        override fun areContentsTheSame(oldItem: GithubRepo, newItem: GithubRepo): Boolean =
            oldItem == newItem
    }
}
