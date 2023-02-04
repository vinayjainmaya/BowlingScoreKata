package com.vinaymj.bowlingscore.domain


class Game {

    private val frameScores = HashMap<Int, FrameScores>()
    private var currentFrameCount = 0
    private lateinit var frame: Frame

    fun updateFrameScore(frameScore: FrameScores) {
        if (!this::frame.isInitialized) frame = Frame()

        currentFrameCount++
        calculateBonus(frameScore)
        val currentScore = frameScores[currentFrameCount - 1]?.gameTotal ?: 0
        frameScore.gameTotal = frame.score(frameScore.first, frameScore.second, currentScore)
        frameScores[currentFrameCount] = frameScore
    }


    private fun calculateBonus(currentFrame: FrameScores) {
        if(frameScores.size == 0) return
        when(frameScores.size) {
            1 -> {
                val lastFrame = frameScores[frameScores.size]
                if (lastFrame != null) {
                    lastFrame.gameTotal = when {
                        (lastFrame.strike && !currentFrame.strike) -> {
                            frame.scoreWithBonus(lastFrame.gameTotal, currentFrame.frameTotal)
                        }
                        (lastFrame.spare) -> {
                            frame.scoreWithBonus(lastFrame.gameTotal,currentFrame.first)
                        }
                        else -> {lastFrame.gameTotal}
                    }
                }
            }
            else -> {
                val lastFrame = frameScores[frameScores.size]
                val secondLastFrame = frameScores[frameScores.size - 1]
                if(lastFrame != null && secondLastFrame != null) {
                    secondLastFrame.gameTotal = when {
                        (secondLastFrame.strike && lastFrame.strike) -> {
                            frame
                                .scoreWithBonus(secondLastFrame.gameTotal,(lastFrame.first + currentFrame.first))
                        }
                        else -> {secondLastFrame.gameTotal}
                    }
                    lastFrame.gameTotal = when {
                        (lastFrame.strike && !currentFrame.strike) -> {
                            frame.scoreWithBonus(secondLastFrame.gameTotal, (lastFrame.first + currentFrame.frameTotal))
                        }
                        (lastFrame.spare) -> {
                            frame.scoreWithBonus(secondLastFrame.gameTotal,(lastFrame.frameTotal + currentFrame.first))
                        }
                        else -> {
                            frame.score(lastFrame.first ,lastFrame.second, secondLastFrame.gameTotal)
                        }
                    }
                }
            }
        }
    }
}