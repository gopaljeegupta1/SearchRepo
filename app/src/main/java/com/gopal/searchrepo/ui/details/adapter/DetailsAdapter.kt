package com.gopal.searchrepo.ui.details.adapter

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
import com.gopal.searchrepo.ui.details.DetailsFragmentDirections

class DetailsAdapter : PagingDataAdapter<Owner, DetailsAdapter.ViewHolder>(REPO_COMPARATOR) {

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Owner>() {
            override fun areItemsTheSame(oldRepo: Owner, newRepo: Owner) = oldRepo.id == newRepo.id

            override fun areContentsTheSame(oldRepo: Owner, newRepo: Owner) = oldRepo == newRepo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemContributorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        binding: ItemContributorBinding,
        owner: Owner
    ): View.OnClickListener {
        return View.OnClickListener {
            val directions = DetailsFragmentDirections.actionDetailsToProfile(owner)
            val extras = FragmentNavigatorExtras(
                binding.avatar to "avatars_${owner.id}"
            )
            it.findNavController().navigate(directions, extras)
        }
    }


    class ViewHolder(val binding: ItemContributorBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, owner: Owner) {

            binding.apply {

                /*image loading*/
                Glide.with(itemView)
                    .load(owner.avatar_url)
                    .centerCrop()
                    .error(android.R.drawable.stat_notify_error)
                    .into(avatar)

                val str = SpannableString(owner.login)
                str.setSpan(
                    StyleSpan(Typeface.BOLD),
                    owner.login.length,
                    str.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                name.text = str

                ViewCompat.setTransitionName(this.avatar, "avatar_${owner.id}")

                root.setOnClickListener(listener)
            }

        }
    }
}