package com.vinaymj.bowlingscore.domain


class Game {

    private val frameScores = HashMap<Int, FrameScores>()
    private var currentFrameCount = 0

    fun updateFrameScore(frameScore: FrameScores) {
        currentFrameCount++
        frameScore.total = when(currentFrameCount) {
            in 2..10 -> {
                calculateBonus(frameScore)
                val currentScore = frameScores[currentFrameCount-1]?.total ?: 0
                Frame().score(frameScore.first, frameScore.second, currentScore)
            }
            else -> Frame().score(frameScore.first, frameScore.second)
        }
        frameScores[currentFrameCount] = frameScore
    }

    private fun calculateBonus(currentFrame: FrameScores) {
        val previousFrame = frameScores[currentFrameCount - 1]
        if (previousFrame != null) {
            previousFrame.total = when {
                (previousFrame.strike) -> {
                    Frame().scoreWithBonus(previousFrame.total, (currentFrame.first + currentFrame.second))
                }
                (previousFrame.spare) -> {
                    Frame().scoreWithBonus(previousFrame.total, currentFrame.first)
                }
                else -> previousFrame.total
            }
        }
    }
}