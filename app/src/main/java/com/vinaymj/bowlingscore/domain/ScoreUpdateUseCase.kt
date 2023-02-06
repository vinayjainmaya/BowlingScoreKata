package com.vinaymj.bowlingscore.domain

import com.vinaymj.bowlingscore.ui.ScoreUiState
import javax.inject.Inject


class ScoreUpdateUseCase @Inject constructor(private val game: Game) {


    fun update(frameScores: FrameScores): ScoreUiState {
        return toScoreUiState(game.updateFrameScore(frameScores))
    }

    fun resetScore(): ScoreUiState {
        return toScoreUiState(game.resetScore())
    }

    private fun toScoreUiState(frames: HashMap<Int, FrameScores>): ScoreUiState {
        return ScoreUiState(
            frames = frames,
            message = game.message
        )
    }
}