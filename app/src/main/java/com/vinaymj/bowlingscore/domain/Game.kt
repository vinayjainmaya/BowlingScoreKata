package com.vinaymj.bowlingscore.domain


class Game {

    private val frameScores = HashMap<Int, FrameScores>()
    private var currentFrame = 0

    fun updateFrameScore(frameScore: FrameScores) {
        currentFrame++
        frameScore.total = when(currentFrame) {
            in 2..10 -> {
                addSpareBonus(frameScore.first)

                val currentScore = frameScores[currentFrame-1]?.total ?: 0
                Frame().score(frameScore.first, frameScore.second, currentScore)
            }
            else -> Frame().score(frameScore.first, frameScore.second)
        }
        frameScores[currentFrame] = frameScore
    }

    private fun addSpareBonus(first: Int) {
        val previousFrame = frameScores[currentFrame - 1]
        if (previousFrame != null) {
            previousFrame.total = if(previousFrame.spare) {
                Frame().score(previousFrame.total,first)
            } else previousFrame.total
        }
    }
}