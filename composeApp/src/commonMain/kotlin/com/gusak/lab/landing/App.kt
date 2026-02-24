package com.gusak.lab.landing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ============================================================================
// MATERIAL DESIGN 3 COLOR SYSTEM
// ============================================================================

object GusakLabColors {
    val Light = lightColorScheme(
        primary = Color(0xFF6750a4),
        onPrimary = Color(0xFFFFFFFF),
        secondary = Color(0xFF625B71),
        onSecondary = Color(0xFFFFFFFF),
        background = Color(0xFFFFFBFE),
        onBackground = Color(0xFF1C1B1F),
        surface = Color(0xFFFFFBFE),
        onSurface = Color(0xFF1C1B1F),
        surfaceVariant = Color(0xFFE7E0EC),
        onSurfaceVariant = Color(0xFF49454E),
        error = Color(0xFFB3261E),
        onError = Color(0xFFFFFFFF)
    )

    val Dark = darkColorScheme(
        primary = Color(0xFFD0BCFF),
        onPrimary = Color(0xFF381E72),
        secondary = Color(0xFFCCC7DB),
        onSecondary = Color(0xFF332D41),
        background = Color(0xFF1C1B1F),
        onBackground = Color(0xFFE6E1E6),
        surface = Color(0xFF1C1B1F),
        onSurface = Color(0xFFE6E1E6),
        surfaceVariant = Color(0xFF49454E),
        onSurfaceVariant = Color(0xFFCAC7D0),
        error = Color(0xFFF2B8B5),
        onError = Color(0xFF601410)
    )
}

@Composable
fun GusakLabTheme(darkTheme: Boolean = false, content: @Composable () -> Unit) {
    val colors = if (darkTheme) GusakLabColors.Dark else GusakLabColors.Light
    val colorScheme = if (darkTheme) {
        darkColorScheme(
            primary = colors.primary,
            onPrimary = colors.onPrimary,
            secondary = colors.secondary,
            onSecondary = colors.onSecondary,
            background = colors.background,
            onBackground = colors.onBackground,
            surface = colors.surface,
            onSurface = colors.onSurface,
            surfaceVariant = colors.surfaceVariant,
            onSurfaceVariant = colors.onSurfaceVariant,
            error = colors.error,
            onError = colors.onError,
            scrim = Color(0xFF000000)
        )
    } else {
        lightColorScheme(
            primary = colors.primary,
            onPrimary = colors.onPrimary,
            secondary = colors.secondary,
            onSecondary = colors.onSecondary,
            background = colors.background,
            onBackground = colors.onBackground,
            surface = colors.surface,
            onSurface = colors.onSurface,
            surfaceVariant = colors.surfaceVariant,
            onSurfaceVariant = colors.onSurfaceVariant,
            error = colors.error,
            onError = colors.onError,
            scrim = Color(0xFF000000)
        )
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = MaterialTheme.typography,
        content = content
    )
}

@Composable
@Preview
fun App() {
    GusakLabTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.background)
        ) {
            Navigation()
            HeroSection()
            FeaturesSection()
            PlatformsSection()
            GettingStartedSection()
            Footer()
        }
    }
}

@Composable
private fun Navigation() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 32.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "GusakLab",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun HeroSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "GusakLab",
                fontSize = 56.sp,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Text(
                text = "A Kotlin Multiplatform Project for Web and Desktop",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.9f),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .widthIn(max = 600.dp)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onPrimary,
                        contentColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("Get Started", fontSize = 16.sp)
                }

                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("Learn More", fontSize = 16.sp)
                }
            }
        }
    }
}

@Composable
private fun FeaturesSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(32.dp)
    ) {
        Text(
            text = "Key Features",
            fontSize = 36.sp,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 48.dp)
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            FeatureCard(
                icon = "🚀",
                title = "Multiplatform",
                description = "Build once, deploy everywhere. Target Web, Desktop, and more with a single codebase."
            )

            FeatureCard(
                icon = "⚡",
                title = "High Performance",
                description = "Leveraging Kotlin's efficiency and Compose's optimizations for blazingly fast applications."
            )

            FeatureCard(
                icon = "🎨",
                title = "Modern UI",
                description = "Create beautiful, responsive user interfaces with Jetpack Compose's declarative approach."
            )

            FeatureCard(
                icon = "🔧",
                title = "Developer Friendly",
                description = "Intuitive APIs, excellent tooling, and comprehensive documentation for smooth development."
            )
        }
    }
}

@Composable
private fun FeatureCard(icon: String, title: String, description: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
            .padding(32.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = icon, fontSize = 40.sp, modifier = Modifier.padding(bottom = 16.dp))
        Text(
            text = title,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = description,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun PlatformsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
            .padding(32.dp)
    ) {
        Text(
            text = "Supported Platforms",
            fontSize = 36.sp,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 48.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PlatformCard(icon = "🌐", name = "Web")
            PlatformCard(icon = "🖥️", name = "Desktop")
        }
    }
}

@Composable
private fun PlatformCard(icon: String, name: String) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = icon, fontSize = 48.sp, modifier = Modifier.padding(bottom = 16.dp))
        Text(text = name, fontSize = 20.sp, color = MaterialTheme.colorScheme.onSurface)
    }
}

@Composable
private fun GettingStartedSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(32.dp)
    ) {
        Text(
            text = "Getting Started",
            fontSize = 36.sp,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 48.dp)
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            StepCard(
                number = "1",
                title = "Clone the Repository",
                description = "Get the latest source code from GitHub and set up your development environment."
            )
            StepCard(
                number = "2",
                title = "Build the Project",
                description = "Use Gradle to build the project. Run './gradlew build' from the project root."
            )
            StepCard(
                number = "3",
                title = "Run on Web",
                description = "Execute './gradlew :composeApp:wasmJsBrowserDevelopmentRun' to run the web app."
            )
            StepCard(
                number = "4",
                title = "Start Developing",
                description = "Modify the code in composeApp/src and see changes in real-time with hot reload."
            )
        }
    }
}

@Composable
private fun StepCard(number: String, title: String, description: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
            .padding(32.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            Text(text = number, color = MaterialTheme.colorScheme.onPrimary, fontSize = 18.sp)
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 24.dp)
        ) {
            Text(
                text = title,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = description,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun Footer() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f))
            .padding(48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "GusakLab © 2026. All rights reserved.",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
        )

        Text(
            text = "Built with Kotlin Multiplatform and Jetpack Compose",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

