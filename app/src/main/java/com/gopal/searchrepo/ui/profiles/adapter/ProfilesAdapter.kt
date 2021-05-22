package com.gopal.searchrepo.ui.profiles.adapter

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gopal.searchrepo.data.model.Owner
import com.gopal.searchrepo.data.model.Repo
import com.gopal.searchrepo.databinding.ItemContributorBinding
import com.gopal.searchrepo.databinding.ItemRepoBinding

class ProfilesAdapter : PagingDataAdapter<Repo, ProfilesAdapter.ViewHolder>(REPO_COMPARATOR) {

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldRepo: Repo, newRepo: Repo) = oldRepo.id == newRepo.id

            override fun areContentsTheSame(oldRepo: Repo, newRepo: Repo) = oldRepo == newRepo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).let { owner ->
            with(holder) {
                itemView.tag = owner
                if (owner != null) {
                    bind(createOnClickListener(binding, owner), owner)
                }
            }
        }
    }

    private fun createOnClickListener(
        binding: ItemRepoBinding,
        owner: Repo
    ): View.OnClickListener {
        return View.OnClickListener {
            /*val directions = DetailsFragmentDirections.actionDetailsToProfile(owner)
            val extras = FragmentNavigatorExtras(
                binding.avatar to "avatars_${owner.id}"
            )
            it.findNavController().navigate(directions, extras)*/
        }
    }


    class ViewHolder(val binding: ItemRepoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, repo: Repo) {

            binding.apply {

                /*hide imageview*/
                avatar.visibility = View.GONE

                val str = SpannableString(repo.owner.login + " / " + repo.name)
                str.setSpan(
                    StyleSpan(Typeface.BOLD),
                    repo.owner.login.length,
                    str.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                name.text = str
                description.text = repo.description
                language.text = repo.language

                root.setOnClickListener(listener)
            }

        }
    }
}