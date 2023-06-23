package com.scotia.androidtakehomeassigment.presentation.feature.github

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scotia.androidtakehomeassigment.domain.model.GithubRepo
import com.scotia.androidtakehomeassigment.domain.model.GithubUser
import com.scotia.androidtakehomeassigment.domain.usecase.GithubRepoUseCase
import com.scotia.androidtakehomeassigment.domain.usecase.GithubUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel class for Github, responsible for preparing and managing the data for an Activity or a Fragment.
 * It also handles the communication of the Activity / Fragment with the rest of the application
 * (e.g. calling the business logic classes).
 *
 * @property userUseCase The use case which fetches the GithubUser data.
 * @property reposUseCase The use case which fetches the GithubRepo data.
 */
@HiltViewModel
class GithubViewModel @Inject constructor(
    private val userUseCase: GithubUserUseCase,
    private val reposUseCase: GithubRepoUseCase
) : ViewModel() {

    private val _userIdSuccess = MutableStateFlow<GithubUser?>(null)
    /**
     * A [StateFlow] representing the success state of fetching a Github user.
     */
    val userIdSuccess: StateFlow<GithubUser?> = _userIdSuccess

    private val _userError = MutableStateFlow<Exception?>(null)
    /**
     * A [StateFlow] representing the error state of fetching a Github user.
     */
    val userError: StateFlow<Exception?> = _userError

    private val _userLoading = MutableStateFlow<Boolean>(false)
    /**
     * A [StateFlow] representing the loading state of fetching a Github user.
     */
    val userLoading: StateFlow<Boolean> = _userLoading

    private val _reposListSuccess = MutableStateFlow<List<GithubRepo>>(emptyList())
    /**
     * A [StateFlow] representing the success state of fetching a list of Github repositories.
     */
    val reposListSuccess: StateFlow<List<GithubRepo>> = _reposListSuccess

    private val _reposError = MutableStateFlow<Exception?>(null)
    /**
     * A [StateFlow] representing the error state of fetching a list of Github repositories.
     */
    val reposError: StateFlow<Exception?> = _reposError

    private val _reposLoading = MutableStateFlow(false)
    /**
     * A [StateFlow] representing the loading state of fetching a list of Github repositories.
     */
    val reposLoading: StateFlow<Boolean> = _reposLoading

    private val _totalForksBadge = MutableStateFlow("")
    /**
     * A [StateFlow] representing the total forks badge of a Github user.
     */
    val totalForksBadge: StateFlow<String> = _totalForksBadge

    /**
     * Sets the user ID and triggers the fetching of user and repository data.
     *
     * @param userId The Github user ID.
     */
    fun setUserId(userId: String){
        getUserId(userId)
        loadRepos(userId)
    }

    /**
     * Fetches a Github user based on the user ID.
     *
     * @param userId The Github user ID.
     */
    private fun getUserId(userId: String) {
        viewModelScope.launch {
            _userLoading.emit(true)
            try {
                _userIdSuccess.emit(userUseCase(userId))
            } catch (e: Exception) {
                _userError.emit(e)
            }
            _userLoading.emit(false)
        }
    }

    /**
     * Fetches a list of Github repositories based on the user ID.
     *
     * @param userId The Github user ID.
     */
    private fun loadRepos(userId: String) {
        viewModelScope.launch {
            _reposLoading.emit(true)
            try {
                _reposListSuccess.emit(reposUseCase(userId))
            } catch (e: Exception) {
                _reposError.emit(e)
            }
            _reposLoading.emit(false)
        }
    }

    /**
     * Fetches the total forks badge for a Github user.
     *
     * @param userId The Github user ID.
     */
    fun loadTotalForksBadge(userId: String) {
        viewModelScope.launch {
            try {
                _totalForksBadge.emit(userUseCase.getTotalForksBadge(userId))
            } catch (e: Exception) {
                _userError.emit(e)
            }
        }
    }
}

