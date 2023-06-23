package com.scotia.androidtakehomeassigment.presentation.feature.github

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scotia.androidtakehomeassigment.domain.model.GithubRepo
import com.scotia.androidtakehomeassigment.domain.model.GithubUser
import com.scotia.androidtakehomeassigment.domain.usecase.GithubRepoUseCase
import com.scotia.androidtakehomeassigment.domain.usecase.GithubUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(
    private val userUseCase: GithubUserUseCase,
    private val reposUseCase: GithubRepoUseCase
) : ViewModel() {
    private val _userIdSuccess = MutableStateFlow<GithubUser?>(null)
    val userIdSuccess: StateFlow<GithubUser?> = _userIdSuccess

    private val _userError = MutableStateFlow<Exception?>(null)
    val userError: StateFlow<Exception?> = _userError

    private val _userLoading = MutableStateFlow<Boolean>(false)
    val userLoading: StateFlow<Boolean> = _userLoading

    private val _reposListSuccess = MutableStateFlow<List<GithubRepo>>(emptyList())
    val reposListSuccess: StateFlow<List<GithubRepo>> = _reposListSuccess

    private val _reposError = MutableStateFlow<Exception?>(null)
    val reposError: StateFlow<Exception?> = _reposError

    private val _reposLoading = MutableStateFlow<Boolean>(false)
    val reposLoading: StateFlow<Boolean> = _reposLoading

    private val _totalForksBadge = MutableStateFlow<String>("")
    val totalForksBadge: StateFlow<String> = _totalForksBadge

    fun setUserId(userId: String){
        getUserId(userId)
        loadRepo(userId)
        Log.i("Test Main 1", userId.toString())
    }

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

    private fun loadRepo(userId: String) {
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
