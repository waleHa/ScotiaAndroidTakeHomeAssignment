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

/**
 * HomeFragment is a [Fragment] subclass that handles the home screen of the application.
 * It fetches and displays Github user data and their repositories using a [GithubViewModel].
 */
@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    /**
     * A binding object that represents the inflated layout fragment_home.
     */
    private lateinit var binding: FragmentHomeBinding

    /**
     * A [GithubViewModel] instance created by delegating to the ViewModel's factory.
     */
    private val viewModel: GithubViewModel by viewModels()

    /**
     * An instance of [RepoListAdapter] to manage the list of repositories displayed in the RecyclerView.
     */
    private val adapter = RepoListAdapter { repo ->
        navigateToDetailFragment(repo)
    }

    /**
     * Initializes the RecyclerView, sets up listeners, and initializes ViewModel observers when the view is created.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        initRecyclerview()

        setupListeners()
        initViewModelObservers()
    }

    /**
     * Initializes the RecyclerView with a LinearLayoutManager and sets the adapter.
     */
    private fun initRecyclerview() {
        binding.repoRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.repoRecyclerView.adapter = adapter
    }

    /**
     * Navigates to DetailFragment when a repository is clicked.
     *
     * @param repo The repository to show details for.
     */
    private fun navigateToDetailFragment(repo: GithubRepo) {
        val bundle = Bundle().apply {
            putParcelable("repo", repo)
        }
        findNavController().navigate(R.id.action_HomeFragment_to_DetailFragment, bundle)
    }

    /**
     * Sets up listeners for UI elements.
     */
    private fun setupListeners() {
        binding.searchTextButton.setOnClickListener {
            hideViewShowError()
            val userId = binding.userIdEditText.text?.trim().toString()
            viewModel.setUserId(userId)
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view?.windowToken, 0)
        }
    }

    /**
     * Hides the user avatar and username views and shows the error view.
     */
    private fun hideViewShowError() {
        binding.avatarImageView.isVisible = false
        binding.userNameTextView.isVisible = false
        binding.imageError.isVisible = true
    }

    /**
     * Shows the user avatar and username views and hides the error view.
     */
    private fun showViewHideError() {
        binding.avatarImageView.isVisible = true
        binding.userNameTextView.isVisible = true
        binding.imageError.isVisible = false
    }

    /**
     * Updates the progress visibility based on the user loading status.
     *
     * @param isLoading True if user data is being loaded, false otherwise.
     */
    private fun userLoadingStatus(isLoading: Boolean) {
        binding.progress.isVisible = isLoading
    }

    /**
     * Displays a Toast with an error message when user data call fails.
     *
     * @param exception The exception occurred during the user data call.
     */
    private fun userCallError(exception: Exception?) {
        exception?.let {
            Toast.makeText(
                requireContext(), it.localizedMessage, Toast.LENGTH_LONG
            ).show()
            hideViewShowError()
        }
    }

    /**
     * Displays the user data when the call to fetch it is successful.
     *
     * @param user The fetched [GithubUser].
     */
    private fun userCallSuccess(user: GithubUser?) {
        binding.user = user
        showViewHideError()
    }

    /**
     * Updates the progress visibility based on the repositories loading status.
     *
     * @param isLoading True if repositories are being loaded, false otherwise.
     */
    private fun repoLoadingStatus(isLoading: Boolean) {
        binding.progress.isVisible = isLoading
    }

    /**
     * Displays a Toast with an error message when the call to fetch repositories fails.
     *
     * @param exception The exception occurred during the call to fetch repositories.
     */
    private fun repoCallError(exception: Exception?) {
        exception?.let {
            Toast.makeText(
                requireContext(), it.localizedMessage, Toast.LENGTH_LONG
            ).show()
        }
    }

    /**
     * Updates the repositories list when the call to fetch them is successful.
     *
     * @param repos The fetched list of [GithubRepo].
     */
    private fun repoCallSuccess(repos: List<GithubRepo>) {
        adapter.submitList(repos)
    }

    /**
     * Initializes observers for ViewModel live data.
     */
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
