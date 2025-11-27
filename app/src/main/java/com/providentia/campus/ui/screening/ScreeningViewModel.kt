package com.providentia.campus.ui.screening

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel

data class Question(val id: Int, val text: String)

data class ScreeningUiState(
    val currentQuestionIndex: Int = 0,
    val answers: MutableMap<Int, Int> = mutableMapOf(),
    val isComplete: Boolean = false,
    val totalScore: Int = 0
)

@HiltViewModel
class ScreeningViewModel @Inject constructor() : ViewModel() {

    val questions = listOf(
        Question(1, "En las últimas 2 semanas, ¿te has sentido nervioso(a) o ansioso(a)?"),
        Question(2, "¿No has podido dejar de preocuparte?"),
        Question(3, "¿Has tenido poco interés o placer en hacer cosas?"),
        Question(4, "¿Te has sentido decaído(a), deprimido(a) o sin esperanza?")
    )

    private val _uiState = MutableStateFlow(ScreeningUiState())
    val uiState: StateFlow<ScreeningUiState> = _uiState.asStateFlow()

    fun selectAnswer(score: Int) {
        val currentIndex = _uiState.value.currentQuestionIndex
        val currentAnswers = _uiState.value.answers.toMutableMap()
        currentAnswers[currentIndex] = score

        if (currentIndex < questions.size - 1) {
            _uiState.update { it.copy(answers = currentAnswers, currentQuestionIndex = currentIndex + 1) }
        } else {
            // Calculate total
            val total = currentAnswers.values.sum()
            _uiState.update { it.copy(answers = currentAnswers, isComplete = true, totalScore = total) }
        }
    }
}
