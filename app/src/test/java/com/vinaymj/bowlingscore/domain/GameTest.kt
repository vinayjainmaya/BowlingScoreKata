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
    fun `first normal frame score`() {
        val frameScore = FrameScores(3,4)

        game.updateFrameScore(frameScore)

        Assert.assertEquals(7,frameScore.total)
    }

    @Test
    fun `second normal frame score`() {
        game.updateFrameScore(FrameScores(3,4))
        val frameScore = FrameScores(7,2)

        game.updateFrameScore(frameScore)

        Assert.assertEquals(16,frameScore.total)
    }

    @Test
    fun `third normal frame score`() {
        game.updateFrameScore(FrameScores(3,4))
        game.updateFrameScore(FrameScores(3,4))

        val frameScore = FrameScores(7,2)
        game.updateFrameScore(frameScore)

        Assert.assertEquals(23,frameScore.total)
    }

    @Test
    fun `fourth normal frame score`() {
        game.updateFrameScore(FrameScores(3,4))
        game.updateFrameScore(FrameScores(3,4))
        game.updateFrameScore(FrameScores(3,4))

        val frameScore = FrameScores(7,2)
        game.updateFrameScore(frameScore)

        Assert.assertEquals(30,frameScore.total)
    }

    @Test
    fun `tenth normal frame score`() {
        performNormalFrameFullGame()

        val frameScore = FrameScores(7,2)
        game.updateFrameScore(frameScore)

        Assert.assertEquals(72,frameScore.total)
    }


    private fun performNormalFrameFullGame() {
        for(i in 1..9) {
            game.updateFrameScore(FrameScores(3,4))
        }
    }

}