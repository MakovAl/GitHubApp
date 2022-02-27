package com.makoval.githubapp.repositories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.makoval.githubapp.databinding.ItemRepositoryBinding
import com.makoval.githubapp.network.Repositories


class RepositoriesAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Repositories, RepositoriesAdapter.RepositoriesViewHolder>(DiffCallback) {

    class RepositoriesViewHolder(
        private var binding: ItemRepositoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(repositories: Repositories) {
            binding.description.text = repositories.description
            binding.name.text = repositories.name
            binding.language.text = repositories.language
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Repositories>() {
        override fun areItemsTheSame(oldItem: Repositories, newItem: Repositories): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Repositories, newItem: Repositories): Boolean {
            return oldItem.name == newItem.name
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepositoriesViewHolder {
        return RepositoriesViewHolder(
            ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: RepositoriesViewHolder, position: Int) {
        val repositories = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(repositories)
        }
        holder.bind(repositories)
    }

    class OnClickListener(val clickListener: (repositories: Repositories) -> Unit) {
        fun onClick(repositories: Repositories) = clickListener(repositories)
    }
}
