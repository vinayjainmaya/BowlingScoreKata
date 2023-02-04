package com.vinaymj.bowlingscore.domain

import javax.inject.Inject


class ScoreUpdateUseCase @Inject constructor(private val game: Game) {


    fun update(frameScores: FrameScores): HashMap<Int, FrameScores> {
        return game.updateFrameScore(frameScores)
    }
}