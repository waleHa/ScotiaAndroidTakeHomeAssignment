package com.scotia.androidtakehomeassigment.presentation.feature.github.Detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.scotia.androidtakehomeassigment.R
import com.scotia.androidtakehomeassigment.databinding.FragmentDetailBinding
import com.scotia.androidtakehomeassigment.domain.model.GithubRepo
import com.scotia.androidtakehomeassigment.presentation.feature.github.GithubViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val viewModel: GithubViewModel by activityViewModels()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)

        val repo = arguments?.getParcelable<GithubRepo>("repo")
        Log.i("Test Detail",repo.toString())
            binding.repo= repo

        // Observing total forks badge
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.totalForksBadge.collect { badge ->
                binding.forksBadgeTextView.text = badge
            }
        }
        repo?.owner?.login?.let { viewModel.loadTotalForksBadge(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
