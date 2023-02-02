package com.vinaymj.bowlingscore.domain


class Game {

    private val frameScores = HashMap<Int, FrameScores>()
    private var currentFrame = 0

    fun updateFrameScore(frameScore: FrameScores) {
        currentFrame++
        frameScore.total = Frame().score(frameScore.first, frameScore.second)
        frameScores[currentFrame] = frameScore
    }
}