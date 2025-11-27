# Providentia Campus MVP

## 📱 Descripción General
**Providentia Campus** es una aplicación móvil nativa para Android diseñada como el primer punto de contacto del ecosistema **Providentia Mind Health**. Su objetivo principal es brindar apoyo de salud mental preventivo y de intervención temprana a estudiantes universitarios en Latinoamérica, utilizando técnicas de Terapia Cognitivo-Conductual (iCBT) y Terapia de Aceptación y Compromiso (ACT).

La aplicación actúa como un "copiloto emocional", ofreciendo herramientas de autoayuda estructuradas, tamizaje de riesgo clínico y rutas de derivación segura en casos de crisis.

## 🎯 Objetivos del Producto
1.  **Accesibilidad**: Proveer soporte de salud mental 24/7 sin barreras económicas o geográficas inmediatas.
2.  **Prevención**: Detectar tempranamente síntomas de ansiedad y depresión mediante tamizajes clínicos validados (PHQ-4, GAD-7).
3.  **Seguridad**: Implementar protocolos robustos de manejo de crisis ("Suicidio Guard") para derivar usuarios de alto riesgo a líneas de emergencia.
4.  **Escalabilidad**: Arquitectura diseñada para soportar miles de usuarios concurrentes con sincronización offline-first.

## 🛠 Arquitectura Técnica

El proyecto sigue los principios de **Clean Architecture** y el patrón de diseño **MVVM (Model-View-ViewModel)**, asegurando la separación de responsabilidades, testabilidad y mantenibilidad.

### Capas de la Aplicación
1.  **Presentation Layer (UI)**:
    *   Construida 100% con **Jetpack Compose** (Material 3).
    *   **ViewModels**: Gestionan el estado de la UI y comunican eventos a la capa de dominio.
    *   **StateFlow**: Para el manejo reactivo de datos en la UI.
2.  **Domain Layer (Business Logic)**:
    *   Contiene los **UseCases** (Casos de Uso) que encapsulan la lógica de negocio pura (ej. `CalculateRiskScoreUseCase`).
    *   Define interfaces de repositorios.
    *   Independiente de frameworks (Android/Firebase).
3.  **Data Layer (Data Access)**:
    *   **Repositories**: Implementaciones concretas que orquestan fuentes de datos (Local vs Remota).
    *   **Local DataSource**: **Room Database** para persistencia offline.
    *   **Remote DataSource**: **Firebase Firestore** para sincronización en la nube.

### Stack Tecnológico
*   **Lenguaje**: Kotlin.
*   **Inyección de Dependencias**: Hilt.
*   **Navegación**: Jetpack Navigation Compose.
*   **Concurrencia**: Coroutines & Flow.
*   **Backend (MVP)**: Firebase Auth & Firestore.

## 📂 Estructura del Proyecto
```
com.providentia.campus
├── data                # Repositorios y Fuentes de Datos
│   ├── local           # Room DB, DAOs
│   ├── remote          # Firebase APIs
│   └── repository      # Implementaciones de Repositorios
├── domain              # Reglas de Negocio
│   ├── model           # Modelos de Dominio
│   ├── repository      # Interfaces
│   └── usecase         # Casos de Uso
├── ui                  # Capa de Presentación
│   ├── auth            # Login/Register
│   ├── home            # Pantalla Principal
│   ├── screening       # Módulo de Tamizaje (PHQ-4)
│   ├── navigation      # Grafo de Navegación
│   └── theme           # Sistema de Diseño
└── di                  # Módulos de Hilt
```

## 🚀 Instalación y Ejecución
Este es un proyecto Android nativo. No se puede ejecutar directamente en un navegador web.

### Prerrequisitos
*   Android Studio Iguana o superior.
*   JDK 17.

### Pasos
1.  Clonar el repositorio.
2.  Abrir en Android Studio.
3.  Sincronizar Gradle (`File > Sync Project with Gradle Files`).
4.  Configurar `google-services.json` (Firebase) en la carpeta `app/`.
5.  Ejecutar en un Emulador Android o Dispositivo Físico.

## 🔒 Seguridad y Privacidad
*   **Encriptación**: Datos sensibles en tránsito (TLS) y en reposo (Firestore rules).
*   **Protocolo de Crisis**: Detección automática de patrones de riesgo en inputs de usuario con bloqueo de flujo y redirección a recursos de emergencia.

---
*Desarrollado por el equipo de Tecnología de Providentia Mind Health.*
