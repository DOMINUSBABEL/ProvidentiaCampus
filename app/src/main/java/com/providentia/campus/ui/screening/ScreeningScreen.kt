package com.providentia.campus.ui.screening

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.providentia.campus.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreeningScreen(
    onScreeningComplete: (Int) -> Unit,
    onBackPressed: () -> Unit = {},
    viewModel: ScreeningViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val questions = viewModel.questions
    var selectedAnswer by remember { mutableStateOf<Int?>(null) }

    if (uiState.isComplete) {
        LaunchedEffect(Unit) {
            onScreeningComplete(uiState.totalScore)
        }
    } else {
        val currentQuestion = questions[uiState.currentQuestionIndex]

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Tamizaje Inicial de Bienestar", color = TextOnDark) },
                    navigationIcon = {
                        IconButton(onClick = onBackPressed) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Atrás",
                                tint = TextOnDark
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = DarkTeal
                    )
                )
            },
            containerColor = DarkTeal
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    // Info Card
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = DarkTealLight
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Un chequeo rápido y confidencial",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    color = TextOnDark,
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Esto nos ayuda a entender tu bienestar actual para personalizar tu experiencia. Tus respuestas son completamente anónimas y seguras.",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = TextOnDark.copy(alpha = 0.85f),
                                    lineHeight = 20.sp
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    // Progress
                    Text(
                        text = "Paso ${uiState.currentQuestionIndex + 1} de ${questions.size}",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = TextOnDark.copy(alpha = 0.7f)
                        ),
                        modifier = Modifier.align(Alignment.Start)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = (uiState.currentQuestionIndex + 1) / questions.size.toFloat(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp),
                        color = CyanAccent,
                        trackColor = DarkTealLight
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    // Question
                    Text(
                        text = currentQuestion.text,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextOnDark,
                            lineHeight = 32.sp
                        )
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    // Answer Options
                    val answers = listOf(
                        Pair(0, "Nunca"),
                        Pair(1, "Varios días"),
                        Pair(2, "Más de la mitad de los días"),
                        Pair(3, "Casi todos los días")
                    )

                    answers.forEach { (value, label) ->
                        AnswerOption(
                            text = label,
                            isSelected = selectedAnswer == value,
                            onClick = { selectedAnswer = value }
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }

                Column {
                    // Continue Button
                    Button(
                        onClick = {
                            selectedAnswer?.let {
                                viewModel.selectAnswer(it)
                                selectedAnswer = null
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        enabled = selectedAnswer != null,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = CyanAccent,
                            contentColor = DarkTeal,
                            disabledContainerColor = DarkTealLight,
                            disabledContentColor = TextOnDark.copy(alpha = 0.5f)
                        ),
                        shape = RoundedCornerShape(28.dp)
                    ) {
                        Text(
                            text = "Continuar",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Crisis Link
                    TextButton(
                        onClick = { /* TODO: Show crisis resources */ },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            "Información de ayuda y crisis",
                            color = CyanAccent,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AnswerOption(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(12.dp),
        color = if (isSelected) CyanAccent.copy(alpha = 0.2f) else DarkTealLight,
        border = if (isSelected) ButtonDefaults.outlinedButtonBorder.copy(
            width = 2.dp,
            brush = androidx.compose.ui.graphics.SolidColor(CyanAccent)
        ) else null
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = TextOnDark,
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                    )
                )
                RadioButton(
                    selected = isSelected,
                    onClick = null,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = CyanAccent,
                        unselectedColor = TextOnDark.copy(alpha = 0.5f)
                    )
                )
            }
        }
    }
}
