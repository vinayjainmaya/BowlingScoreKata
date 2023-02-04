package com.vinaymj.bowlingscore.domain

import org.junit.Assert
import org.junit.Before
import org.junit.Test


class ScoreUpdateUseCaseTest {

    private val frame: Frame = Frame()
    private val game: Game = Game(frame)
    private lateinit var useCase: ScoreUpdateUseCase

    @Before
    fun setUp() {
        useCase = ScoreUpdateUseCase(game)
    }

    @Test
    fun `updating bowling score`() {
        val frameScore = FrameScores(
            4,5
        )
        val gameScores = useCase.update(frameScore)
        Assert.assertEquals(1, gameScores.size)
        Assert.assertEquals(9, gameScores[gameScores.size]?.gameTotal)
    }

    @Test
    fun `reset bowling score`() {
        val gameScore = useCase.resetScore()

        Assert.assertEquals(0, gameScore.size)
    }
}