package com.vinaymj.bowlingscore.ui

import androidx.lifecycle.ViewModel
import com.vinaymj.bowlingscore.domain.FrameScores
import com.vinaymj.bowlingscore.domain.ScoreUpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: ScoreUpdateUseCase
): ViewModel() {

    private val _scoreState = MutableStateFlow(ScoreUiState())
    val scoreState: StateFlow<ScoreUiState> = _scoreState.asStateFlow()

    fun updateScore(frameScore: FrameScores) {
        val scoreUiState = useCase.update(frameScore)
        _scoreState.update {
            it.copy(
                frames = scoreUiState.frames,
                error = scoreUiState.error
            )
        }
    }

    fun resetScore() {
        val scoreUiState = useCase.resetScore()
        _scoreState.update {
            it.copy(
                frames = scoreUiState.frames,
                error = scoreUiState.error
            )
        }
    }
}