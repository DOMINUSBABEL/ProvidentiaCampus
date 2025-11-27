# Providentia Campus MVP

<p align="center">
  <strong>Tu copiloto emocional universitario</strong><br>
  Salud mental digital accesible para estudiantes de Latinoamérica
</p>

---

## 📱 Descripción General

**Providentia Campus** es una aplicación móvil nativa para Android diseñada como el primer módulo visible del ecosistema **Providentia Mind Health**. Su objetivo principal es democratizar el acceso a herramientas de salud mental preventiva y de intervención temprana para estudiantes universitarios en Latinoamérica (16-29 años), una población desproporcionadamente afectada por ansiedad, depresión y estrés académico.

La aplicación actúa como un "copiloto emocional", ofreciendo autoayuda estructurada basada en **Terapia Cognitivo-Conductual por Internet (iCBT)** y **Terapia de Aceptación y Compromiso (ACT)**. No reemplaza la terapia profesional, sino que complementa los servicios de bienestar universitario como una primera línea de apoyo escalable.

### Problema que Resuelve

En Colombia y otros países de Latinoamérica, existe una brecha crítica en el acceso a servicios de salud mental:
- **1 psicólogo por cada 100,000 habitantes** en zonas rurales (OMS, 2022).
- **40% de estudiantes universitarios** reportan síntomas clínicos de ansiedad/depresión sin tratamiento.
- **Estigma social** que dificulta la búsqueda de ayuda presencial.
- **Recursos limitados** en centros de bienestar universitario (1 psicólogo por 3,000+ estudiantes).

Providentia Campus aborda esta brecha mediante:
1. **Escalabilidad**: Una app puede atender a miles de usuarios concurrentemente.
2. **Anonimato Inicial**: Reduce el estigma de buscar ayuda.
3. **Disponibilidad 24/7**: Acceso inmediato a herramientas de regulación emocional.
4. **Derivación Inteligente**: Detecta casos de alto riesgo y conecta con líneas de crisis.

---

## 🎯 Objetivos del Producto

### Objetivos Clínicos
1.  **Reducción de Síntomas Subclínicos**: Disminuir puntajes en escalas de ansiedad (GAD-7) y depresión (PHQ-9) en un 20-30% tras 6 semanas de uso.
2.  **Prevención de Escalada**: Detectar tempranamente patrones de ideación suicida y derivar a intervención humana.
3.  **Alfabetización en Salud Mental**: Educar a usuarios sobre síntomas, técnicas de afrontamiento y cuándo buscar ayuda profesional.

### Objetivos Tecnológicos
1.  **Accesibilidad**: Funcionar en dispositivos Android de gama media-baja (6.0+) con conectividad intermitente.
2.  **Seguridad de Datos**: Cumplir con normativas de protección de datos (GDPR-like en Colombia).
3.  **Escalabilidad**: Arquitectura preparada para soportar 50,000+ usuarios concurrentes en Fase 2.

### Objetivos de Negocio
1.  **Validación del Modelo**: Obtener métricas de engagement (DAU/MAU > 0.4) para justificar escalamiento.
2.  **Alianzas Institucionales**: Integración con 5-10 universidades piloto en Colombia durante 2025.
3.  **Preparación para Ecosistema**: Sentar las bases del "Data Core" compartido con módulos futuros (APS, Senior, Trabajo).

---

## 🌍 El Ecosistema Providentia Mind Health (Visión 3-5 Años)

Providentia Campus es el **Fase 1** de un ecosistema modular más amplio:

### Núcleo Duro (Core Shared Infrastructure)
1.  **APS Core**: Motor de IA conversacional para soporte emocional.
2.  **Data Core**: Base de datos centralizada para análisis de cohortes y aprendizaje federado.
3.  **Suicidio Guard**: Protocolo de detección y derivación de riesgo suicida (transversal a todos los módulos).
4.  **Content Engine**: Sistema de gestión de contenido clínico (módulos de iCBT/ACT reutilizables).
5.  **Implementation Hub**: Herramientas para instituciones (dashboards de métricas agregadas, no datos individuales).

### Módulos Aplicativos (Segunda Ola)
- **Providentia Senior**: Adaptación para adultos mayores (soledad, duelo, ansiedad por envejecimiento).
- **Providentia Trabajo**: Salud mental ocupacional (burnout, estrés laboral, relaciones laborales).
- **Providentia Familia**: Apoyo a cuidadores y padres (crianza, comunicación familiar).

### Flujo de Datos entre Módulos
```
Campus → Data Core → Análisis Agregado → Content Engine (mejora de módulos)
         ↓
      Suicidio Guard (alertas cross-módulo)
         ↓
   Implementation Hub (métricas para universidades)
```

**Privacidad del Usuario**: Los datos individuales jamás se comparten con instituciones. Solo métricas agregadas y anónimas (ej. "30% de usuarios mejoraron en PHQ-9" sin identificar a personas).

---

## 🛠 Arquitectura Técnica

El proyecto sigue los principios de **Clean Architecture** y el patrón de diseño **MVVM (Model-View-ViewModel)**, asegurando:
- **Separación de responsabilidades**: Lógica de negocio independiente de frameworks.
- **Testabilidad**: Cada capa tiene interfaces claramente definidas para mocking.
- **Mantenibilidad**: Cambios en la UI no afectan la lógica de negocio.

### Capas de la Aplicación

#### 1. Presentation Layer (UI)
*   **Framework**: Jetpack Compose 100% (Material 3).
*   **Componentes**:
    *   **ViewModels**: Gestionan el estado de la UI usando `StateFlow`.
    *   **Composables**: Funciones declarativas que reaccionan a cambios de estado.
*   **Diseño**:
    *   Paleta de colores: Dark Teal (#1A3A3A) + Cyan Accent (#4ECDC4) + Warm Beige (#F5E6D3).
    *   Tipografía: Sans-serif moderna, optimizada para lectura prolongada.

#### 2. Domain Layer (Business Logic)
*   **Responsabilidades**:
    *   Cálculo de puntajes de riesgo (PHQ-4, GAD-7).
    *   Reglas de derivación (ej. "Si PHQ-9 > 20 → Mostrar pantalla de crisis").
    *   Generación de recomendaciones de módulos según perfil del usuario.
*   **Componentes**:
    *   **UseCases**: Encapsulan operaciones de negocio (ej. `CalculateRiskScoreUseCase`, `GetNextModuleUseCase`).
    *   **Repository Interfaces**: Contratos para acceso a datos (implementados en la capa de datos).
*   **Independencia**: No conoce nada sobre Android, Firebase o Room.

#### 3. Data Layer (Data Access)
*   **Repositories**: Orquestan fuentes de datos (Local + Remota).
*   **Local DataSource**:
    *   **Room Database**: Persistencia offline (tablas: `users`, `screenings`, `module_progress`).
    *   **Sincronización**: Los datos se guardan localmente primero, luego se sincronizan con Firebase cuando hay conectividad.
*   **Remote DataSource**:
    *   **Firebase Firestore**: Base de datos NoSQL en la nube.
    *   **Firebase Auth**: Gestión segura de usuarios (Email/Password + Google Sign-In).
    *   **Cloud Functions**: Lógica de lado del servidor (ej. triggers de alertas de crisis).

### Stack Tecnológico Detallado

| Componente | Tecnología | Justificación |
|------------|------------|---------------|
| **Lenguaje** | Kotlin | Lenguaje oficial de Android, conciso y seguro. |
| **UI Framework** | Jetpack Compose | Declarativo, menos boilerplate, hot reload. |
| **Inyección de Dependencias** | Hilt | Integración nativa con Android, reduce código manual. |
| **Navegación** | Navigation Compose | Sistema de rutas tipadas para Compose. |
| **Concurrencia** | Coroutines & Flow | Manejo asíncrono eficiente, reemplazo de callbacks. |
| **Backend (MVP)** | Firebase (Auth + Firestore + Functions) | Rapidez de desarrollo, escalabilidad automática. |
| **Base de Datos Local** | Room | ORM oficial de Android, SQL type-safe. |
| **Testing** | JUnit + Compose Test | Unit tests + UI tests automatizados. |

### Estrategia Offline-First

1.  **Instalación**: Al abrir la app por primera vez, se descargan los primeros 3 módulos de contenido (texto + audio).
2.  **Uso Sin Red**: El usuario puede completar módulos offline. El progreso se guarda en Room.
3.  **Sincronización**: Cuando hay conectividad, Room → Firestore (con manejo de conflictos).

---

## 📂 Estructura del Proyecto

```
com.providentia.campus/
├── data/                     # Capa de Datos
│   ├── local/                # Persistencia Local (Room)
│   │   ├── dao/              # Data Access Objects
│   │   ├── entities/         # Entidades de Room
│   │   └── ProvidentiaDatabase.kt
│   ├── remote/               # APIs Remotas (Firebase)
│   │   ├── FirebaseAuthSource.kt
│   │   └── FirestoreDataSource.kt
│   └── repository/           # Implementaciones de Repositorios
│       ├── UserRepository.kt
│       └── ScreeningRepository.kt
├── domain/                   # Lógica de Negocio
│   ├── model/                # Modelos de Dominio
│   │   ├── User.kt
│   │   ├── Screening.kt
│   │   └── Module.kt
│   ├── repository/           # Interfaces de Repositorios
│   │   └── IUserRepository.kt
│   └── usecase/              # Casos de Uso
│       ├── CalculateRiskUseCase.kt
│       └── GetNextModuleUseCase.kt
├── ui/                       # Capa de Presentación
│   ├── onboarding/           # Pantalla de Bienvenida
│   │   └── WelcomeScreen.kt
│   ├── auth/                 # Autenticación
│   │   ├── LoginScreen.kt
│   │   └── AuthViewModel.kt
│   ├── screening/            # Tamizaje (PHQ-4)
│   │   ├── ScreeningScreen.kt
│   │   └── ScreeningViewModel.kt
│   ├── home/                 # Pantalla Principal
│   │   └── HomeScreen.kt
│   ├── navigation/           # Grafo de Navegación
│   │   └── NavGraph.kt
│   └── theme/                # Sistema de Diseño
│       ├── Color.kt
│       ├── Theme.kt
│       └── Typography.kt
└── di/                       # Inyección de Dependencias (Hilt)
    └── AppModule.kt
```

---

## 🧠 Metodología Clínica

### Base Teórica: iCBT + ACT

**1. Terapia Cognitivo-Conductual por Internet (iCBT)**
- **Objetivo**: Identificar y reestructurar pensamientos automáticos negativos.
- **Técnicas Implementadas**:
  - Registro de Pensamientos (Journaling).
  - Desafío de Distorsiones Cognitivas (ej. "Todo o Nada", "Catastrofización").
  - Activación Conductual (planificación de actividades gratificantes).

**2. Terapia de Aceptación y Compromiso (ACT)**
- **Objetivo**: Aumentar la flexibilidad psicológica (vivir conforme a valores personales a pesar del malestar).
- **Técnicas Implementadas**:
  - Mindfulness (ejercicios de respiración guiada).
  - Clarificación de Valores (ej. "¿Qué es importante para ti en tu vida académica?").
  - Defusión Cognitiva (ver pensamientos como eventos mentales, no verdades absolutas).

### Estructura de Módulos (6-8 Sesiones)

| Módulo | Objetivo | Duración | Técnicas Clave |
|--------|----------|----------|----------------|
| **1. Entendiendo la Ansiedad** | Psicoeducación sobre síntomas fisiológicos y cognitivos. | 15 min | Modelo de activación (pensamiento → emoción → conducta) |
| **2. Activación Conductual** | Romper el ciclo de evitación mediante planificación de actividades. | 20 min | Registro de actividades, jerarquía de tareas |
| **3. Respiración y Grounding** | Herramientas de regulación somática. | 10 min | Respiración diafragmática 4-7-8, técnica 5-4-3-2-1 |
| **4. Reestructuración Cognitiva** | Identificar y desafiar pensamientos distorsionados. | 25 min | Registro ABC (Activador-Creencia-Consecuencia) |
| **5. Defusión Cognitiva (ACT)** | Cambiar la relación con los pensamientos. | 20 min | Metáfora de "hojas en el río" |
| **6. Valores y Acción Comprometida** | Alinear comportamiento con valores personales. | 30 min | Matriz de valores, plan de acción |

### Escalas de Tamizaje Utilizadas

- **PHQ-4**: 4 ítems, detección rápida de ansiedad/depresión.
- **GAD-7**: 7 ítems, severidad de ansiedad generalizada.
- **PHQ-9**: 9 ítems, severidad de depresión (aplicado si PHQ-4 > umbral).
- **Ítem de Ideación Suicida**: Pregunta directa sobre pensamientos suicidas en últimas 2 semanas.

**Umbrales de Derivación**:
- PHQ-9 > 20 o GAD-7 > 15 → Pantalla de recursos de crisis.
- Respuesta afirmativa a ideación suicida → Bloqueo inmediato del flujo normal + mostrar línea 106.

---

## 🔒 Seguridad y Privacidad

### Encriptación
- **En Tránsito**: TLS 1.3 para todas las comunicaciones con Firebase.
- **En Reposo**: Firestore encripta datos automáticamente en servidores. Room DB encriptado con SQLCipher (futuro).

### Gestión de Datos Sensibles
- **Respuestas de Tamizaje**: Almacenadas localmente con timestamp. Solo puntajes agregados se suben a Firestore.
- **Contenido de Diarios**: Nunca sale del dispositivo sin consentimiento explícito del usuario.

### Protocolo de Crisis (Suicidio Guard)
1.  **Detección**: Si el usuario responde afirmativamente a ideación suicida o puntajes > umbral crítico.
2.  **Bloqueo de Flujo Normal**: No se permite continuar con módulos regulares.
3.  **Pantalla de Seguridad**:
    - Botón grande: "Llamar Línea 106" (marcación directa).
    - Listado de recursos locales (hospitales, centros de crisis).
    - Opción de contactar a un amigo/familiar de confianza.
4.  **No Almacenamiento de Transcripciones**: Las conversaciones con la IA (Reframe Bot) no se guardan por defecto.

### Consentimiento Informado
- **Al Primer Uso**: Modal obligatorio explicando:
  - Qué datos se recopilan (puntajes de escalas, tiempos de uso).
  - Qué NO se recopila (contenido de diarios, transcripciones de chat IA).
  - Derecho a eliminar cuenta y datos en cualquier momento.

---

## 🚀 Instalación y Ejecución

### Prerrequisitos
*   **Android Studio**: Iguana (2023.2.1) o superior.
*   **JDK**: 17 (recomendado).
*   **Cuenta de Firebase**: Proyecto configurado con Firestore y Authentication habilitados.

### Pasos de Instalación

1.  **Clonar el repositorio**:
    ```bash
    git clone https://github.com/DOMINUSBABEL/ProvidentiaCampus.git
    cd ProvidentiaCampus
    ```

2.  **Configurar Firebase**:
    - Descargar `google-services.json` desde la consola de Firebase.
    - Colocar el archivo en `app/google-services.json`.

3.  **Abrir en Android Studio**:
    - `File > Open` y seleccionar la carpeta del proyecto.

4.  **Sincronizar Gradle**:
    - Android Studio detectará las dependencias automáticamente.
    - `File > Sync Project with Gradle Files`.

5.  **Ejecutar la App**:
    - Conectar un dispositivo Android físico (Android 8.0+) O iniciar un emulador.
    - Click en el botón "Run" (▶️).

### Configuración de Emulador (Recomendado)
- **Dispositivo**: Pixel 5 o superior.
- **Android Version**: API 31 (Android 12) o superior.
- **RAM**: 2GB mínimo.

---

### Funcionalidades Clave (Fase 1 + 2)
- **Onboarding & Triage**: Tamizaje clínico validado.
- **Asistente AI (Therapist Bot)**:
    - Chat conversacional para desahogo emocional (simulado en MVP).
    - **Generación de Informes**: Crea un resumen clínico de la sesión para facilitar la derivación a terapeutas humanos.
- **Ruta de Aprendizaje**: Módulos de autoayuda (en desarrollo).

## 📱 Descargar APK (Demo)

Para probar la aplicación en tu dispositivo Android:

1.  Ve a la pestaña **Actions** en este repositorio.
2.  Haz clic en el último flujo de trabajo **Android Build**.
3.  Baja a la sección **Artifacts**.
4.  Descarga el archivo `providentia-campus-debug`.
5.  Descomprime el zip e instala el `app-debug.apk` en tu teléfono.

> **Nota**: Al ser una versión de depuración (debug), es posible que Google Play Protect muestre una advertencia. Es seguro instalarla para pruebas.

## 🧪 Testing

### Niveles de Testing

#### 1. Unit Tests (JUnit)
- **Ubicación**: `test/` folder.
- **Objetivo**: Validar lógica de negocio aislada.
- **Ejemplo**:
  ```kotlin
  @Test
  fun `calculateRiskScore returns HIGH when PHQ9 greater than 20`() {
      val score = CalculateRiskUseCase().execute(phq9 = 22)
      assertEquals(RiskLevel.HIGH, score)
  }
  ```

#### 2. UI Tests (Compose Test)
- **Ubicación**: `androidTest/` folder.
- **Objetivo**: Verificar navegación y flujos de usuario.
- **Ejemplo**:
  ```kotlin
  @Test
  fun welcomeScreen_clickBeginJourney_navigatesToLogin() {
      composeTestRule.setContent {
          ProvidentiaNavGraph()
      }
      composeTestRule.onNodeWithText("Comenzar mi camino").performClick()
      composeTestRule.onNodeWithText("Iniciar Sesión").assertIsDisplayed()
  }
  ```

#### 3. Integration Tests (Manual - Fase 1)
- **Escenarios**:
  - Usuario completa tamizaje con alto riesgo → Debe ver pantalla de crisis.
  - Usuario pierde conectividad a mitad de módulo → Progreso se guarda localmente.

---

## 📊 Roadmap y Futuras Funcionalidades

### Fase 1 (MVP - 3-4 meses) ✅ EN PROGRESO
- [x] Onboarding y diseño UI.
- [x] Autenticación (Email + Google).
- [x] Tamizaje inicial (PHQ-4).
- [ ] Sistema de módulos (6 módulos de iCBT/ACT).
- [ ] Herramientas rápidas (Respiración, Grounding).
- [ ] Protocolo Suicidio Guard completo.
- [ ] Beta testing con 100 estudiantes.

### Fase 2 (Escalamiento - 6 meses)
- [ ] Integración con universidades piloto (3-5 instituciones).
- [ ] Dashboard de métricas agregadas para instituciones.
- [ ] Reframe Bot (IA generativa para reestructuración cognitiva).
- [ ] Notificaciones push inteligentes (recordatorios de módulos).
- [ ] Análisis de sentimientos en inputs de usuario (detección temprana de deterioro).

### Fase 3 (Expansión del Ecosistema - 12 meses)
- [ ] Lanzamiento de Providentia Senior (adaptación para adultos mayores).
- [ ] Data Core compartido entre Campus y Senior.
- [ ] Aprendizaje federado (mejora de modelos sin compartir datos individuales).
- [ ] Integración con sistemas de salud universitarios (derivación directa a psicólogos).

---

## 🤝 Contribuciones

Este es un proyecto de código privado en Fase 1. Una vez alcanzada la estabilidad de producto, se evaluará la apertura de código bajo licencia open-source (probablemente GPL-3.0).

### Equipo Actual
- **Juan Gómez**: Desarrollador Full Stack y Asesor Jurídico.
- **César Garzón**: Desarrollador Full Stack de Salud.

### Contacto
- **Email**: contacto@providentiamindhealth.com
- **LinkedIn**: [Enlace al equipo]

---

## 📄 Licencia

**Propietario**: Providentia Mind Health.  
**Licencia**: Todos los derechos reservados (Fase 1). Se planea transición a código abierto en Fase 2.

---

*Desarrollado con ❤️ para democratizar la salud mental en Latinoamérica.*
