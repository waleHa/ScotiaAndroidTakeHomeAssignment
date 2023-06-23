package com.scotia.androidtakehomeassigment.presentation.feature.github.home

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.scotia.androidtakehomeassigment.R
import com.scotia.androidtakehomeassigment.domain.model.GithubRepo
import com.scotia.androidtakehomeassigment.databinding.FragmentHomeBinding
import com.scotia.androidtakehomeassigment.domain.model.GithubUser

import com.scotia.androidtakehomeassigment.presentation.feature.github.GithubViewModel
import com.scotia.androidtakehomeassigment.presentation.feature.github.adapter.RepoListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: GithubViewModel by viewModels()

    // Update your adapter initialization
    private val adapter = RepoListAdapter { repo ->
        navigateToDetailFragment(repo)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        binding.repoRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.repoRecyclerView.adapter = adapter

        setupListeners()
        initViewModelObservers()
    }

    // Use this function to navigate to DetailFragment
    private fun navigateToDetailFragment(repo: GithubRepo) {
        val bundle = Bundle().apply {
            putParcelable("repo", repo)
        }
        findNavController().navigate(R.id.action_HomeFragment_to_DetailFragment, bundle)
    }
    
    private fun setupListeners() {
        // On button click, pass user ID to the ViewModel
        binding.searchTextButton.setOnClickListener {
            hideViewShowError()
            val userId = binding.userIdEditText.text?.trim().toString()
            viewModel.setUserId(userId)
            // Hide the keyboard
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view?.windowToken, 0)
        }
    }

    private fun hideViewShowError() {
        binding.avatarImageView.isVisible = false
        binding.userNameTextView.isVisible = false
        binding.imageError.isVisible = true
    }

    private fun showViewHideError() {
        binding.avatarImageView.isVisible = true
        binding.userNameTextView.isVisible = true
        binding.imageError.isVisible = false
    }

    private fun userLoadingStatus(isLoading: Boolean) {
        binding.progress.isVisible = isLoading
    }

    private fun userCallError(exception: Exception?) {
        exception?.let {
            Toast.makeText(
                requireContext(), it.localizedMessage, Toast.LENGTH_LONG
            ).show()
            hideViewShowError()
        }
    }

    private fun userCallSuccess(user: GithubUser?) {
        binding.user = user
        showViewHideError()
    }

    private fun repoLoadingStatus(isLoading: Boolean) {
        binding.progress.isVisible = isLoading
    }

    private fun repoCallError(exception: Exception?) {
        exception?.let {
            Toast.makeText(
                requireContext(), it.localizedMessage, Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun repoCallSuccess(repos: List<GithubRepo>) {
        adapter.submitList(repos)
    }

    private fun initViewModelObservers() {
        lifecycleScope.launch {
            viewModel.userLoading.collect(::userLoadingStatus)
        }
        lifecycleScope.launch {
            viewModel.userError.collect(::userCallError)
        }
        lifecycleScope.launch {
            viewModel.userIdSuccess.collect(::userCallSuccess)
        }
        lifecycleScope.launch {
            viewModel.reposLoading.collect(::repoLoadingStatus)
        }
        lifecycleScope.launch {
            viewModel.reposError.collect(::repoCallError)
        }
        lifecycleScope.launch {
            viewModel.reposListSuccess.collect(::repoCallSuccess)
        }
    }
}
