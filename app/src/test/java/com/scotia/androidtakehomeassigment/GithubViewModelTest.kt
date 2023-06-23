package com.scotia.androidtakehomeassigment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.scotia.androidtakehomeassigment.domain.model.GithubRepo
import com.scotia.androidtakehomeassigment.domain.model.GithubUser
import com.scotia.androidtakehomeassigment.domain.usecase.GithubRepoUseCase
import com.scotia.androidtakehomeassigment.domain.usecase.GithubUserUseCase
import com.scotia.androidtakehomeassigment.presentation.feature.github.GithubViewModel
import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

/**
 * Unit tests for the [GithubViewModel] class.
 */
@RunWith(MockitoJUnitRunner::class)
class GithubViewModelTest {

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val mockUserUseCase: GithubUserUseCase = mock(GithubUserUseCase::class.java)
    private val mockRepoUseCase: GithubRepoUseCase = mock(GithubRepoUseCase::class.java)
    private lateinit var githubViewModel: GithubViewModel

    @Before
    fun setUp() {
        githubViewModel = GithubViewModel(mockUserUseCase, mockRepoUseCase)
    }

    /**
     * Test case for verifying that the [setUserId] function updates the LiveData correctly.
     */
    @Test
    fun `setUserId updates LiveData correctly`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val userId = "octocat"
        val user = GithubUser("The Octocat", "https://avatars3.githubusercontent.com/u/583231?v=4")
        val repoList = listOf<GithubRepo>()
        `when`(mockUserUseCase(userId)).thenReturn(user)
        `when`(mockRepoUseCase(userId)).thenReturn(repoList)

        githubViewModel.setUserId(userId)

        assertEquals(user, githubViewModel.userIdSuccess.value)
        assertEquals(repoList, githubViewModel.reposListSuccess.value)
    }
}
