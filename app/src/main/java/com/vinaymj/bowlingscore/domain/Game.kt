package com.vinaymj.bowlingscore.domain


class Game {

    private val frameScores = HashMap<Int, FrameScores>()
    private lateinit var frame: Frame

    fun updateFrameScore(frameScore: FrameScores) {
        if (!this::frame.isInitialized) frame = Frame()

        calculateBonus(frameScore)

        val currentScore = frameScores[frameScores.size]?.gameTotal ?: 0
        frameScore.gameTotal = if (frameScores.size == 9) {
            frame.score(frameScore.frameTotal, frameScore.third, currentScore)
        } else {
            frame.score(frameScore.first, frameScore.second, currentScore)
        }

        frameScores[frameScores.size + 1] = frameScore
    }


    private fun calculateBonus(currentFrame: FrameScores) {
        when (frameScores.size) {
            0 -> return
            1 -> firstFrameBonus(currentFrame)
            else -> {
                val lastFrame = frameScores[frameScores.size]
                val secondLastFrame = frameScores[frameScores.size - 1]
                if (lastFrame != null && secondLastFrame != null) {
                    secondLastFrame.gameTotal =
                        multiStrikeSequenceBonus(secondLastFrame, lastFrame, currentFrame)
                    lastFrame.gameTotal =
                        previousFrameBonus(lastFrame, secondLastFrame, currentFrame)
                }
            }
        }
    }

    private fun previousFrameBonus(
        lastFrame: FrameScores,
        secondLastFrame: FrameScores,
        currentFrame: FrameScores
    ) = when {
        (frameScores.size == 9) -> {
            tenthFrameBonus(lastFrame, secondLastFrame, currentFrame)
        }
        (lastFrame.strike && !currentFrame.strike) -> {
            frame.scoreWithBonus(
                secondLastFrame.gameTotal, (lastFrame.first + currentFrame.frameTotal)
            )
        }
        (lastFrame.spare) -> {
            frame.scoreWithBonus(
                secondLastFrame.gameTotal, (lastFrame.frameTotal + currentFrame.first)
            )
        }
        else -> {
            frame.score(lastFrame.first, lastFrame.second, secondLastFrame.gameTotal)
        }
    }

    private fun multiStrikeSequenceBonus(
        secondLastFrame: FrameScores,
        lastFrame: FrameScores,
        currentFrame: FrameScores
    ) = when {
        (secondLastFrame.strike && lastFrame.strike) -> {
            frame.scoreWithBonus(secondLastFrame.gameTotal, (lastFrame.first + currentFrame.first))
        }
        else -> {
            secondLastFrame.gameTotal
        }
    }

    private fun firstFrameBonus(currentFrame: FrameScores) {
        val lastFrame = frameScores[frameScores.size]
        if (lastFrame != null) {
            lastFrame.gameTotal = when {
                (lastFrame.strike && !currentFrame.strike) -> {
                    frame.scoreWithBonus(lastFrame.gameTotal, currentFrame.frameTotal)
                }
                (lastFrame.spare) -> {
                    frame.scoreWithBonus(lastFrame.gameTotal, currentFrame.first)
                }
                else -> {
                    lastFrame.gameTotal
                }
            }
        }
    }

    private fun tenthFrameBonus(
        lastFrame: FrameScores,
        secondLastFrame: FrameScores,
        currentFrame: FrameScores
    ) = when {
        (lastFrame.strike) -> {
            frame.scoreWithBonus(
                secondLastFrame.gameTotal, (lastFrame.first + currentFrame.frameTotal)
            )
        }
        (lastFrame.spare) -> {
            frame.scoreWithBonus(
                secondLastFrame.gameTotal, (lastFrame.frameTotal + currentFrame.first)
            )
        }
        else -> {
            frame.score(lastFrame.first, lastFrame.second, secondLastFrame.gameTotal)
        }
    }
}