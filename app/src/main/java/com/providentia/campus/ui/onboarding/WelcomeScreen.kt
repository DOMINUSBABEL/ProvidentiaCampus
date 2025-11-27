package com.providentia.campus.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.providentia.campus.ui.theme.WarmBeige
import com.providentia.campus.ui.theme.DarkTeal
import com.providentia.campus.ui.theme.CyanAccent
import com.providentia.campus.ui.theme.TextOnDark

@Composable
fun WelcomeScreen(onGetStarted: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top section - Beige with illustration
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.45f)
                .background(WarmBeige),
            contentAlignment = Alignment.Center
        ) {
            // Placeholder for illustration - in production, use actual drawable
            Text(
                text = "🧘‍♀️",
                fontSize = 80.sp,
                modifier = Modifier.padding(32.dp)
            )
        }

        // Bottom section - Dark Teal with content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.55f)
                .background(DarkTeal)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Te damos la bienvenida\na Providentia Campus",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = TextOnDark,
                        textAlign = TextAlign.Center,
                        fontSize = 28.sp
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Tu espacio de apoyo digital para manejar el estrés académico, la ansiedad y el ánimo, basado en técnicas de TCC y ACT.",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = TextOnDark.copy(alpha = 0.9f),
                        textAlign = TextAlign.Center,
                        lineHeight = 22.sp
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Info card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = DarkTeal.copy(alpha = 0.7f)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Info",
                            tint = CyanAccent,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Información importante",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    color = TextOnDark,
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Recuerda, esto es un apoyo, no reemplaza la terapia profesional. Si estás en una crisis, busca ayuda de inmediato.",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = TextOnDark.copy(alpha = 0.85f),
                                    lineHeight = 18.sp
                                )
                            )
                        }
                    }
                }
            }

            // CTA Button
            Button(
                onClick = onGetStarted,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CyanAccent,
                    contentColor = DarkTeal
                ),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text(
                    text = "Comenzar mi camino",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }
    }
}
