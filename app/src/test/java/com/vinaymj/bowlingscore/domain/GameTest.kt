package com.vinaymj.bowlingscore.domain

import org.junit.Assert
import org.junit.Before
import org.junit.Test


class GameTest {

    private lateinit var game: Game

    @Before
    fun setUp() {
        game = Game(Frame())
    }

    @Test
    fun `first normal frame score`() {
        val frameScore = FrameScores(3,4)

        game.updateFrameScore(frameScore)

        Assert.assertEquals(7,frameScore.gameTotal)
    }

    @Test
    fun `second normal frame score`() {
        game.updateFrameScore(FrameScores(3,4))
        val frameScore = FrameScores(7,2)

        game.updateFrameScore(frameScore)

        Assert.assertEquals(16,frameScore.gameTotal)
    }

    @Test
    fun `third normal frame score`() {
        performNormalFrameFullGame(2, 3, 4)

        val frameScore = FrameScores(7,2)
        game.updateFrameScore(frameScore)

        Assert.assertEquals(23,frameScore.gameTotal)
    }

    @Test
    fun `fourth normal frame score`() {
        performNormalFrameFullGame(3, 3, 4)

        val frameScore = FrameScores(7,2)
        game.updateFrameScore(frameScore)

        Assert.assertEquals(30,frameScore.gameTotal)
    }

    @Test
    fun `tenth normal frame score`() {
        performNormalFrameFullGame(9, 3, 4)

        val frameScore = FrameScores(7,2)
        game.updateFrameScore(frameScore)

        Assert.assertEquals(72,frameScore.gameTotal)
    }

    @Test
    fun `first spare frame score`() {
        game.updateFrameScore(FrameScores(5,5))

        val frameScore = FrameScores(7,2)
        game.updateFrameScore(frameScore)

        Assert.assertEquals(26,frameScore.gameTotal)
    }

    @Test
    fun `second frame is spare score`() {
        game.updateFrameScore(FrameScores(5,4))
        game.updateFrameScore(FrameScores(6,4))

        val frameScore = FrameScores(3,5)
        game.updateFrameScore(frameScore)

        Assert.assertEquals(30,frameScore.gameTotal)
    }

    @Test
    fun `strike frame score`() {
        game.updateFrameScore(FrameScores(10,0))

        val frameScore = FrameScores(3,5)
        game.updateFrameScore(frameScore)

        Assert.assertEquals(26,frameScore.gameTotal)
    }

    @Test
    fun `double strike frame score`() {
        game.updateFrameScore(FrameScores(10,0))
        game.updateFrameScore(FrameScores(10,0))


        val frameScore = FrameScores(5,3)
        game.updateFrameScore(frameScore)

        Assert.assertEquals(51,frameScore.gameTotal)
    }

    @Test
    fun `random 7 frame score`() {
        game.updateFrameScore(FrameScores(10,0))
        game.updateFrameScore(FrameScores(10,0))
        game.updateFrameScore(FrameScores(5,0))
        game.updateFrameScore(FrameScores(5,3))
        game.updateFrameScore(FrameScores(7,3))
        game.updateFrameScore(FrameScores(10,0))


        val frameScore = FrameScores(5,5)
        game.updateFrameScore(frameScore)

        Assert.assertEquals(103,frameScore.gameTotal)
    }

    @Test
    fun `random 9 frame score`() {
        game.updateFrameScore(FrameScores(5,5))
        game.updateFrameScore(FrameScores(3,5))
        game.updateFrameScore(FrameScores(6,3))
        game.updateFrameScore(FrameScores(8,1))
        game.updateFrameScore(FrameScores(10,0))
        game.updateFrameScore(FrameScores(3,7))
        game.updateFrameScore(FrameScores(8,0))
        game.updateFrameScore(FrameScores(6,2))

        val frameScore = FrameScores(1,1)
        game.updateFrameScore(frameScore)

        Assert.assertEquals(95,frameScore.gameTotal)
    }

    @Test
    fun `full strike frame score`() {
        performNormalFrameFullGame(9,10,0)

        val frameScore = FrameScores(10,10,10)
        game.updateFrameScore(frameScore)

        Assert.assertEquals(300,frameScore.gameTotal)
    }

    @Test
    fun `full spare frame score`() {
        performNormalFrameFullGame(9,5,5)

        val frameScore = FrameScores(5,5,5)
        game.updateFrameScore(frameScore)

        Assert.assertEquals(150,frameScore.gameTotal)
    }

    @Test
    fun `normal full frame score`() {
        performNormalFrameFullGame(9,9,0)

        val frameScore = FrameScores(9,0,0)
        game.updateFrameScore(frameScore)

        Assert.assertEquals(90,frameScore.gameTotal)
    }

    @Test
    fun `reset game score`() {
        val resetScore = game.resetScore()
        Assert.assertEquals(0,resetScore.size)
    }

    private fun performNormalFrameFullGame(count: Int, first: Int, second: Int) {
        for(i in 1..count) {
            game.updateFrameScore(FrameScores(first,second))
        }
    }

}