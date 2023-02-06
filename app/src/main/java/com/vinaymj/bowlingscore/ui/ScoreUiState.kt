package com.vinaymj.bowlingscore.ui

import com.vinaymj.bowlingscore.domain.FrameScores

data class ScoreUiState(
    val frames: MutableMap<Int, FrameScores> = mutableMapOf(),
    val error: Boolean = false
)
