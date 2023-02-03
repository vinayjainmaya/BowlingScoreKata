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
        performNormalFrameFullGame(2)

        val frameScore = FrameScores(7,2)
        game.updateFrameScore(frameScore)

        Assert.assertEquals(23,frameScore.total)
    }

    @Test
    fun `fourth normal frame score`() {
        performNormalFrameFullGame(3)

        val frameScore = FrameScores(7,2)
        game.updateFrameScore(frameScore)

        Assert.assertEquals(30,frameScore.total)
    }

    @Test
    fun `tenth normal frame score`() {
        performNormalFrameFullGame(9)

        val frameScore = FrameScores(7,2)
        game.updateFrameScore(frameScore)

        Assert.assertEquals(72,frameScore.total)
    }

    @Test
    fun `first spare frame score`() {
        game.updateFrameScore(FrameScores(5,5))

        val frameScore = FrameScores(7,2)
        game.updateFrameScore(frameScore)

        Assert.assertEquals(26,frameScore.total)
    }

    @Test
    fun `second frame is spare score`() {
        game.updateFrameScore(FrameScores(5,4))
        game.updateFrameScore(FrameScores(6,4))

        val frameScore = FrameScores(3,5)
        game.updateFrameScore(frameScore)

        Assert.assertEquals(30,frameScore.total)
    }

    private fun performNormalFrameFullGame(count: Int) {
        for(i in 1..count) {
            game.updateFrameScore(FrameScores(3,4))
        }
    }

}