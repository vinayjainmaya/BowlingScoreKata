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

    fun updateScore(firstPoint: String, secondPoint: String, thirdPoint: String) {
        if(firstPoint.isBlank() || secondPoint.isBlank() || thirdPoint.isBlank()) return

        val scoreUiState = useCase.update(FrameScores(
            firstPoint.toInt(),secondPoint.toInt(),thirdPoint.toInt()
        ))

        val toMutableMap = scoreUiState.frames.toMutableMap()
        updatingFrameValues(toMutableMap)

        _scoreState.update {
            it.copy(
                frames = toMutableMap,
                error = scoreUiState.error
            )
        }
    }

    fun resetScore() {
        val scoreUiState = useCase.resetScore()
        val toMutableMap = scoreUiState.frames.toMutableMap()
        updatingFrameValues(toMutableMap)
        _scoreState.update {
            it.copy(
                frames = toMutableMap,
                error = scoreUiState.error
            )
        }
    }

    private fun updatingFrameValues(toMutableMap: MutableMap<Int, FrameScores>) {
        toMutableMap.forEach { (index, frameScores) ->
            toMutableMap[index] = frameScores.copy(
                first = frameScores.first,
                second = frameScores.second,
                third = frameScores.third,
                gameTotal = frameScores.gameTotal,
            )
        }
    }
}