package com.gopal.searchrepo.ui.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gopal.searchrepo.databinding.ReposLoadStateFooterBinding

class DetailsLoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<DetailsLoadStateAdapter.LoadStateViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = ReposLoadStateFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class LoadStateViewHolder(private val binding: ReposLoadStateFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnRetry.setOnClickListener{
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                progress.isVisible = loadState is LoadState.Loading
                btnRetry.isVisible = loadState !is LoadState.Loading
                error.isVisible = loadState !is LoadState.Loading
            }
        }

    }

}