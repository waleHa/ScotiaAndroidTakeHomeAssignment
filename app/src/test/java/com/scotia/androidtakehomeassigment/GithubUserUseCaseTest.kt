package com.scotia.androidtakehomeassigment

import com.scotia.androidtakehomeassigment.domain.model.GithubRepo
import com.scotia.androidtakehomeassigment.domain.repository.github.GithubRepository
import com.scotia.androidtakehomeassigment.domain.usecase.GithubUserUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/**
 * Unit tests for the [GithubUserUseCase] class.
 */
@RunWith(MockitoJUnitRunner::class)
class GithubUserUseCaseTest {

    private val mockRepository: GithubRepository = mock(GithubRepository::class.java)
    private lateinit var githubUserUseCase: GithubUserUseCase

    @Before
    fun setUp() {
        githubUserUseCase = GithubUserUseCase(mockRepository)
    }

    /**
     * Test case for verifying that the [getTotalForks] function returns the correct sum of forks.
     */
    @Test
    fun `getTotalForks returns correct sum`() = runTest {
        val userId = "octocat"
        val repoList = listOf(
            GithubRepo("repo1", "desc1", "2019-01-01T01:01:01Z", 1, 10, null),
            GithubRepo("repo2", "desc2", "2019-01-01T01:01:01Z", 1, 20, null)
        )
        `when`(mockRepository.getRepo(userId)).thenReturn(repoList)
        val result = githubUserUseCase.getTotalForks(userId)
        assertEquals(30, result)
    }
}
