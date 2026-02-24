package com.gusak.lab.landing.app.landing

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gusaklab.composeapp.generated.resources.Res
import gusaklab.composeapp.generated.resources.goose
import org.jetbrains.compose.resources.painterResource

@Preview
@Composable
fun LandingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.background)
    ) {
        Header()
        Spacer(modifier = Modifier.weight(1f))
        Footer()
    }
}

@Composable
private fun Header() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Circular Image - goose.jpeg
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
        ) {
            Image(
                painter = painterResource(Res.drawable.goose),
                contentDescription = "Gusak Lab Logo",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Company Name
        Text(
            text = "Gusak Lab",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Email Address
        Text(
            text = "gusak.lab@gmail.com",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Company Description
        Text(
            text = "At Gusak Lab, we create innovative puzzle games that challenge your mind and provide hours of entertainment. Our games combine beautiful design with engaging gameplay to deliver unforgettable experiences.",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            lineHeight = 24.sp,
            modifier = Modifier
                .widthIn(max = 500.dp)
                .padding(horizontal = 16.dp)
        )
    }
}

@Composable
private fun Footer() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Download PuzzleScroll",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 400.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
            ) {
                Text("iOS", fontSize = 14.sp)
            }

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
            ) {
                Text("Android", fontSize = 14.sp)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "© 2026 Gusak Lab. All rights reserved.",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}

