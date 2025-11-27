package com.providentia.campus.ui.screening

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ScreeningScreen(
    onScreeningComplete: (Int) -> Unit,
    viewModel: ScreeningViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val questions = viewModel.questions

    if (uiState.isComplete) {
        LaunchedEffect(Unit) {
            onScreeningComplete(uiState.totalScore)
        }
    } else {
        val currentQuestion = questions[uiState.currentQuestionIndex]

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LinearProgressIndicator(
                progress = (uiState.currentQuestionIndex + 1) / questions.size.toFloat(),
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = currentQuestion.text,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(32.dp))

            AnswerButton("Para nada (0)", 0) { viewModel.selectAnswer(0) }
            AnswerButton("Varios días (1)", 1) { viewModel.selectAnswer(1) }
            AnswerButton("Más de la mitad de los días (2)", 2) { viewModel.selectAnswer(2) }
            AnswerButton("Casi todos los días (3)", 3) { viewModel.selectAnswer(3) }
        }
    }
}

@Composable
fun AnswerButton(text: String, value: Int, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        Text(text)
    }
}
