package com.vinaymj.bowlingscore.ui

import com.vinaymj.bowlingscore.domain.FrameScores

data class ScoreUiState(
    val frames: HashMap<Int, FrameScores>? = null,
    val message: String? = null
)
