package com.scotia.androidtakehomeassigment.presentation

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.scotia.androidtakehomeassigment.databinding.ActivityMainBinding
import com.scotia.androidtakehomeassigment.domain.model.GithubRepo
import com.scotia.androidtakehomeassigment.domain.model.GithubUser
import com.scotia.androidtakehomeassigment.presentation.feature.github.GithubViewModel
import com.scotia.androidtakehomeassigment.presentation.feature.github.adapter.RepoListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: GithubViewModel by viewModels()
    private val adapter = RepoListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the RecyclerView
        binding.repoRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.repoRecyclerView.adapter = adapter

        // Set up listeners and observers
        setupListeners()
        initViewModelObservers()
    }

    fun hideViewShowError() {
        binding.avatarImageView.isVisible = false
        binding.userNameTextView.isVisible = false
        binding.imageError.isVisible = true
    }

    fun showViewHideView() {
        binding.avatarImageView.isVisible = true
        binding.userNameTextView.isVisible = true
        binding.imageError.isVisible = false
    }

    private fun setupListeners() {
        // On button click, pass user ID to the ViewModel
        binding.searchTextButton.setOnClickListener {
            hideViewShowError()
            val userId = binding.userIdEditText.text?.trim().toString()
            viewModel.setUserId(userId)
            // Get the current focused view
            val view = this.currentFocus

            // Create an InputMethodManager instance
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            // Hide the keyboard
            imm.hideSoftInputFromWindow(view?.windowToken, 0)
        }
    }

    // Helper function to show/hide the loading indicator
    private fun userLoadingStatus(isLoading: Boolean) {
        binding.progress.isVisible = isLoading
    }

    // Helper function to handle user call errors
    private fun userCallError(exception: Exception?) {
        if (exception != null) {
            Toast.makeText(
                this, exception.localizedMessage.toString(), Toast.LENGTH_LONG
            ).show()
            hideViewShowError()
        }
    }

    // Helper function to handle successful user calls
    private fun userCallSuccess(user: GithubUser?) {
        Log.i("Test Main 1", user.toString())
        binding.user = user
        Log.i("Test Main 2", binding.user.toString())
        showViewHideView()
    }

    // Helper function to show/hide the loading indicator
    private fun repoLoadingStatus(isLoading: Boolean) {
        binding.progress.isVisible = isLoading
    }

    // Helper function to handle repos call errors
    private fun repoCallError(exception: Exception?) {
        if (exception != null) {
            Toast.makeText(
                this, exception.localizedMessage.toString(), Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun repoCallSuccess(repos: List<GithubRepo>) {
        adapter.submitList(repos)
    }

    private fun initViewModelObservers() {
        lifecycleScope.launch {
            // Observe loading state and update UI accordingly
            viewModel.userLoading.collect(::userLoadingStatus)
        }
        lifecycleScope.launch {
            // Observe errors and show error messages
            viewModel.userError.collect(::userCallError)
        }
        lifecycleScope.launch {
            // Observe successful user data and update UI accordingly
            viewModel.userIdSuccess.collect(::userCallSuccess)
        }
        lifecycleScope.launch {
            // Observe repositories loading state and update UI accordingly
            viewModel.reposLoading.collect(::repoLoadingStatus)
        }
        lifecycleScope.launch {
            // Observe repositories errors and show error messages
            viewModel.reposError.collect(::repoCallError)
        }
        lifecycleScope.launch {
            // Observe successful repositories data and update the list of repositories
            viewModel.reposListSuccess.collect(::repoCallSuccess)
        }
    }
}
