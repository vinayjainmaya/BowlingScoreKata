package com.vinaymj.bowlingscore.domain


class Game {

    private val frameScores = HashMap<Int, FrameScores>()
    private var currentFrame = 0

    fun updateFrameScore(frameScore: FrameScores) {
        currentFrame++
        frameScore.total = when(currentFrame) {
            in 2..10 -> {
                val currentScore = frameScores[currentFrame-1]?.total ?: 0
                Frame().score(frameScore.first, frameScore.second, currentScore)
            }
            else -> Frame().score(frameScore.first, frameScore.second)
        }
        frameScores[currentFrame] = frameScore
    }
}