package com.vinaymj.bowlingscore.ui

import com.vinaymj.bowlingscore.domain.FrameScores
import com.vinaymj.bowlingscore.domain.ScoreUpdateUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class MainViewModelTest {

    private val useCase = mockk<ScoreUpdateUseCase>()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = MainViewModel(useCase)
    }

    @Test
    fun `update the score`() {
        val frameScore = FrameScores(
            5,3
        )
        val mockScore = ScoreUiState(
            frames = hashMapOf(1 to FrameScores(
                5,3,0,8
            ))
        )
        every { useCase.update(frameScore) } returns mockScore

        viewModel.updateScore(frameScore)

        val value = viewModel.scoreState.value
        Assert.assertEquals(1,value.frames?.size)
        Assert.assertEquals(8,value.frames?.get(value.frames?.size)?.gameTotal)
    }


}