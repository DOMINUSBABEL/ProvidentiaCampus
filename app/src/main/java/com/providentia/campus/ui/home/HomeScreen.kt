package com.providentia.campus.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.providentia.campus.ui.theme.*

@Composable
fun HomeScreen(
    onNavigateToChat: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkTeal)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bienvenido a Providentia",
            style = MaterialTheme.typography.headlineMedium.copy(color = TextOnDark, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 32.dp)
        )
        
        Spacer(modifier = Modifier.height(24.dp))

        // Daily Path Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = DarkTealLight
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Tu Ruta de Hoy",
                    style = MaterialTheme.typography.titleMedium.copy(color = TextOnDark, fontWeight = FontWeight.SemiBold)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Módulo 1: Entendiendo la Ansiedad",
                    style = MaterialTheme.typography.bodyMedium.copy(color = TextOnDark)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { /* TODO: Navigate to Module */ },
                    colors = ButtonDefaults.buttonColors(containerColor = CyanAccent, contentColor = DarkTeal)
                ) {
                    Text("Continuar")
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // AI Therapist Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = WarmBeige
            ),
            shape = RoundedCornerShape(16.dp),
            onClick = onNavigateToChat
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Chat,
                    contentDescription = "Chat",
                    tint = DarkTeal,
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "Hablar con el Asistente",
                        style = MaterialTheme.typography.titleMedium.copy(color = DarkTeal, fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = "Conversa y genera un informe",
                        style = MaterialTheme.typography.bodySmall.copy(color = DarkTeal.copy(alpha = 0.8f))
                    )
                }
            }
        }
    }
}
