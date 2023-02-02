package com.vinaymj.bowlingscore.domain

import org.junit.Assert
import org.junit.Before
import org.junit.Test


class GameTest {

    private lateinit var game: Game

    @Before
    fun setUp() {
        game = Game()
    }

    @Test
    fun `first frame score`() {
        val frameScore = FrameScores(3,4)

        game.updateFrameScore(frameScore)

        Assert.assertEquals(7,frameScore.total)
    }
}