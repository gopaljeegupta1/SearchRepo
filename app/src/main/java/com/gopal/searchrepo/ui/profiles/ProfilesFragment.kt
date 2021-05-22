package com.gopal.searchrepo.ui.profiles

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.gopal.searchrepo.R
import com.gopal.searchrepo.databinding.FragmentProfilesBinding
import com.gopal.searchrepo.ui.details.adapter.DetailsLoadStateAdapter
import com.gopal.searchrepo.ui.profiles.adapter.ProfilesAdapter
import com.gopal.searchrepo.ui.profiles.adapter.ProfilesLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfilesFragment : Fragment(R.layout.fragment_profiles) {

    private val viewModel by viewModels<ProfilesViewModel>()
    private var _binding: FragmentProfilesBinding? = null
    private val binding get() = _binding!!
    private val args: ProfilesFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        _binding = FragmentProfilesBinding.bind(view)

        val adapter = ProfilesAdapter()

        binding.apply {
//            name.text = args.repo.name
            username.text = args.details.login
//            description.text = args.repo.description

            /*img loading*/
            avatar.apply {
                transitionName = args.details.avatar_url
                Glide.with(view)
                    .load(args.details.avatar_url)
                    .error(android.R.drawable.stat_notify_error)
                    .into(this)
            }


            /*bind recycle view*/
            recyclerRepository.apply {
                setHasFixedSize(true)
                itemAnimator = null
                this.adapter = adapter.withLoadStateHeaderAndFooter(
                    header = ProfilesLoadStateAdapter { adapter.retry() },
                    footer = ProfilesLoadStateAdapter { adapter.retry() }
                )
                postponeEnterTransition()
                viewTreeObserver.addOnPreDrawListener {
                    startPostponedEnterTransition()
                    true
                }
            }

            btnRetry.setOnClickListener {
                adapter.retry()
            }
        }

        ViewCompat.setTransitionName(binding.avatar, "avatar_${args.details.id}")

        /*api calling*/
        viewModel.searchContributor(args.details.login)
        viewModel.repos.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        /*bind loadMore data*/
        adapter.addLoadStateListener { loadState ->
            binding.apply {
                progress.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerRepository.isVisible = loadState.source.refresh is LoadState.NotLoading
                btnRetry.isVisible = loadState.source.refresh is LoadState.Error
                error.isVisible = loadState.source.refresh is LoadState.Error

                // no results found
                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    adapter.itemCount < 1
                ) {
                    recyclerRepository.isVisible = false
                    emptyTv.isVisible = true
                } else {
                    emptyTv.isVisible = false
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}