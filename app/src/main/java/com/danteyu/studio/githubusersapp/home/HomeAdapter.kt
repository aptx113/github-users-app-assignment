package com.danteyu.studio.githubusersapp.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.danteyu.studio.githubusersapp.R
import com.danteyu.studio.githubusersapp.data.GitHubUser
import com.danteyu.studio.githubusersapp.databinding.ItemHomeBinding
import kotlinx.android.synthetic.main.item_home.view.*

/**
 * Created by George Yu on 2020/4/27.
 *
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * [GitHubUser], including computing diffs between lists.
 */
class HomeAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<GitHubUser, HomeAdapter.GitHubUserViewHolder>(DiffCallback) {

    /**
     * Custom listener that handles clicks on [RecyclerView] items.  Passes the [GitHubUser]
     * associated with the current item to the [onClick] function.
     * @param clickListener lambda that will be called with the current [GitHubUser]
     */
    class OnClickListener(val clickListener: (gitHubUser: GitHubUser) -> Unit) {
        fun onClick(gitHubUser: GitHubUser) = clickListener(gitHubUser)
    }

    class GitHubUserViewHolder(private var binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(gitHubUser: GitHubUser) {
            binding.gitHubUser = gitHubUser
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [GitHubUser]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<GitHubUser>() {
        override fun areItemsTheSame(oldItem: GitHubUser, newItem: GitHubUser): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: GitHubUser, newItem: GitHubUser): Boolean {
            return oldItem.id == newItem.id
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubUserViewHolder {
        return GitHubUserViewHolder(
            ItemHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: GitHubUserViewHolder, position: Int) {
        val gitHubUser = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(gitHubUser)
        }
        holder.bind(gitHubUser)
    }
}