package com.scotia.androidtakehomeassigment.presentation.feature.github.Detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.scotia.androidtakehomeassigment.R
import com.scotia.androidtakehomeassigment.databinding.FragmentDetailBinding
import com.scotia.androidtakehomeassigment.domain.model.GithubRepo
import com.scotia.androidtakehomeassigment.presentation.feature.github.GithubViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
/**
 * This is the [DetailFragment] class which displays details about a specific GitHub repository.
 */
@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {
    /**
     * ViewModel for the GitHub activity. This is shared across fragments.
     */
    private val viewModel: GithubViewModel by activityViewModels()

    private var _binding: FragmentDetailBinding? = null
    // The non-null assertion (!!) operator is used here to avoid NullPointerExceptions
    private val binding get() = _binding!!

    /**
     * Called immediately after onCreateView(LayoutInflater, ViewGroup, Bundle) has returned,
     * but before any saved state has been restored in to the view.
     *
     * @param view The View returned by onCreateView(LayoutInflater, ViewGroup, Bundle).
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous
     * saved state as given here.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)

        // get the repository from the arguments
        val repo = arguments?.getParcelable<GithubRepo>("repo")
        binding.repo = repo

        // Observing total forks badge
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.totalForksBadge.collect { badge ->
                binding.forksBadgeTextView.text = badge
            }
        }
        repo?.owner?.login?.let { viewModel.loadTotalForksBadge(it) }
    }

    /**
     * Called when the view previously created by onCreateView(LayoutInflater, ViewGroup, Bundle)
     * has been detached from the fragment.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
