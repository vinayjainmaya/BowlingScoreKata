package com.vinaymj.bowlingscore.domain


class Game {

    private val frameScores = HashMap<Int, FrameScores>()
    private var currentFrame = 0

    fun updateFrameScore(frameScore: FrameScores) {
        currentFrame++
        frameScore.total = when(currentFrame) {
            in 2..10 -> {
                val previousTotal = frameScores[currentFrame-1]?.total ?: 0
                previousTotal + frameScore.first + frameScore.second
            }
            else -> Frame().score(frameScore.first, frameScore.second)
        }
        frameScores[currentFrame] = frameScore
    }
}