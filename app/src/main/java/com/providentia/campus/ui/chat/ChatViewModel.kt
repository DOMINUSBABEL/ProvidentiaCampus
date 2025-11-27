package com.providentia.campus.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.providentia.campus.domain.model.ChatMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ChatUiState(
    val messages: List<ChatMessage> = emptyList(),
    val isTyping: Boolean = false,
    val report: String? = null
)

@HiltViewModel
class ChatViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    init {
        // Initial greeting
        addBotMessage("Hola. Soy tu asistente de apoyo emocional. ¿Qué te gustaría conversar hoy?")
    }

    fun sendMessage(content: String) {
        if (content.isBlank()) return

        // User message
        val userMsg = ChatMessage(content = content, isUser = true)
        _uiState.update { it.copy(messages = it.messages + userMsg, isTyping = true) }

        // Simulate AI processing
        viewModelScope.launch {
            delay(1500) // Simulate network delay
            val response = generateMockResponse(content)
            addBotMessage(response)
            _uiState.update { it.copy(isTyping = false) }
        }
    }

    private fun addBotMessage(content: String) {
        val botMsg = ChatMessage(content = content, isUser = false)
        _uiState.update { it.copy(messages = it.messages + botMsg) }
    }

    private fun generateMockResponse(input: String): String {
        val lowerInput = input.lowercase()
        return when {
            lowerInput.contains("triste") || lowerInput.contains("deprimid") -> 
                "Lamento que te sientas así. Es válido sentir tristeza. ¿Ha pasado algo específico que haya detonado este sentimiento?"
            lowerInput.contains("ansios") || lowerInput.contains("nervios") -> 
                "La ansiedad puede ser abrumadora. ¿Te gustaría intentar un ejercicio de respiración rápida para calmarnos un poco?"
            lowerInput.contains("estrés") || lowerInput.contains("examen") || lowerInput.contains("trabajo") -> 
                "El estrés académico es muy común. Recuerda ir paso a paso. ¿Qué es lo que más te preocupa de esa tarea?"
            lowerInput.contains("morir") || lowerInput.contains("suicid") -> 
                "Siento que estés pasando por un momento tan oscuro. Por favor, recuerda que no estás solo. Si sientes que estás en peligro, por favor llama a la línea 106 o ve a urgencias. Estoy aquí para escucharte, pero tu seguridad es lo primero."
            else -> "Te escucho. Cuéntame más sobre eso. ¿Cómo te hace sentir?"
        }
    }

    fun generateReport() {
        viewModelScope.launch {
            _uiState.update { it.copy(isTyping = true) }
            delay(2000)
            
            val currentDate = java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault()).format(java.util.Date())
            
            val summary = buildString {
                append("INFORME CLÍNICO PRELIMINAR\n")
                append("Generado por: Providentia AI Assistant\n")
                append("Fecha: $currentDate\n\n")
                
                append("RESUMEN DE LA SESIÓN:\n")
                append("El usuario ha interactuado con el asistente emocional. ")
                
                val messages = _uiState.value.messages
                val userMessages = messages.filter { it.isUser }
                
                if (userMessages.any { it.content.contains("triste") }) {
                    append("Se detectaron indicadores de bajo estado de ánimo. ")
                }
                if (userMessages.any { it.content.contains("ansios") }) {
                    append("El usuario reportó síntomas de ansiedad. ")
                }
                
                append("\n\nTRANSCRIPCIÓN RELEVANTE:\n")
                messages.takeLast(6).forEach { msg ->
                    val prefix = if (msg.isUser) "Usuario" else "IA"
                    append("$prefix: ${msg.content}\n")
                }
                
                append("\nRECOMENDACIÓN:\n")
                append("Se sugiere seguimiento por parte de un profesional clínico para profundizar en los temas tratados.")
            }
            
            _uiState.update { it.copy(report = summary, isTyping = false) }
        }
    }
    
    fun clearReport() {
        _uiState.update { it.copy(report = null) }
    }
}
