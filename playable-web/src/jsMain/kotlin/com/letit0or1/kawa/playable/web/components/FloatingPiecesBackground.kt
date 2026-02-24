package com.letit0or1.kawa.playable.web.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.letit0or1.kawa.playable.core.CellState
import com.letit0or1.kawa.playable.core.PuzzlePiece
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

private val A = CellState.Active
private val I = CellState.Inactive

// Predefined piece shapes for background
private val backgroundPieceShapes = listOf(
    // L-shape
    listOf(
        listOf(A, I),
        listOf(A, I),
        listOf(A, A),
    ),
    // T-shape
    listOf(
        listOf(A, A, A),
        listOf(I, A, I),
    ),
    // Square
    listOf(
        listOf(A, A),
        listOf(A, A),
    ),
    // Line
    listOf(
        listOf(A, A, A),
    ),
    // Z-shape
    listOf(
        listOf(A, A, I),
        listOf(I, A, A),
    ),
    // S-shape
    listOf(
        listOf(I, A, A),
        listOf(A, A, I),
    ),
)

private data class FloatingPiece(
    val id: Int,
    val piece: PuzzlePiece,
    val startX: Float,
    val startY: Float,
    val rotation: Float,
    val scale: Float,
    val speed: Float,
    val alpha: Float,
)

/**
 * Subtle animated background with floating puzzle pieces.
 * Uses Lissajous curve for smooth, organic movement.
 * Each piece follows the same trajectory but with its own phase offset.
 * 
 * Lissajous curve formula:
 * x = A * sin(a*t + δ)
 * y = B * sin(b*t)
 * 
 * Where a/b ratio determines the shape of the curve.
 */
@Composable
fun FloatingPiecesBackground(
    modifier: Modifier = Modifier,
    pieceCount: Int = 12,
    cellSize: Dp = 10.dp,
    baseAlpha: Float = 0.08f,
) {
    // Base positions (center points for each piece's Lissajous orbit)
    val basePositions = listOf(
        // Left column
        0.12f to 0.12f,
        0.10f to 0.40f,
        0.08f to 0.68f,
        0.14f to 0.88f,
        // Center-left column
        0.30f to 0.20f,
        0.32f to 0.52f,
        0.28f to 0.82f,
        // Center column
        0.50f to 0.15f,
        0.48f to 0.48f,
        0.52f to 0.78f,
        // Center-right column
        0.70f to 0.22f,
        0.72f to 0.55f,
        0.68f to 0.85f,
        // Right column
        0.88f to 0.18f,
        0.90f to 0.50f,
        0.86f to 0.80f,
    )

    // Infinite animation - time parameter for Lissajous curve
    val infiniteTransition = rememberInfiniteTransition(label = "floatingPieces")
    
    // Main time parameter (0 to 2π over 40 seconds)
    val time by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2f * kotlin.math.PI.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 40000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "time"
    )
    
    // Slow rotation animation
    val rotationTime by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 60000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    BoxWithConstraints(modifier = modifier) {
        val screenWidth = maxWidth
        val screenHeight = maxHeight
        
        // Lissajous parameters for X, Y movement
        val amplitudeX = 0.06f  // 6% of screen width movement
        val amplitudeY = 0.08f  // 8% of screen height movement
        val freqRatioA = 3f    // Frequency ratio for X
        val freqRatioB = 2f    // Frequency ratio for Y (3:2 creates nice figure-8 like pattern)
        val freqRatioZ = 5f    // Frequency ratio for Z (depth) - different ratio for more organic feel
        
        // Depth effect parameters
        val minScale = 0.6f    // Scale when "far away"
        val maxScale = 1.4f    // Scale when "close"
        val minAlphaMultiplier = 0.4f  // Alpha multiplier when "far"
        val maxAlphaMultiplier = 1.2f  // Alpha multiplier when "close"
        
        basePositions.take(pieceCount).forEachIndexed { index, (baseX, baseY) ->
            val shape = backgroundPieceShapes[index % backgroundPieceShapes.size]
            val piece = remember(index) { 
                PuzzlePiece(grid = shape, mask = (index % 8) + 1) 
            }
            
            // Phase offset for each piece (spread evenly across the cycle)
            val phaseOffset = (index.toFloat() / pieceCount) * 2f * kotlin.math.PI.toFloat()
            
            // Speed variation per piece
            val speedFactor = 0.7f + (index % 4) * 0.15f
            
            // Lissajous curve calculation with phase offset
            val t = time * speedFactor + phaseOffset
            val xOffset = amplitudeX * sin(freqRatioA * t + kotlin.math.PI.toFloat() / 4f)
            val yOffset = amplitudeY * sin(freqRatioB * t)
            
            // Z-axis (depth) using third Lissajous component
            // Value oscillates between -1 and 1
            val zValue = sin(freqRatioZ * t + kotlin.math.PI.toFloat() / 3f)
            
            // Map z-value to scale (closer = bigger)
            val depthScale = minScale + (maxScale - minScale) * ((zValue + 1f) / 2f)
            
            // Map z-value to alpha multiplier (closer = more visible)
            val depthAlphaMultiplier = minAlphaMultiplier + (maxAlphaMultiplier - minAlphaMultiplier) * ((zValue + 1f) / 2f)
            
            // Convert relative position to actual Dp
            val actualX = screenWidth * (baseX + xOffset)
            val actualY = screenHeight * (baseY + yOffset)
            
            // Base variation per piece + depth effect
            val baseScaleVariation = 0.9f + (index % 3) * 0.1f
            val pieceScale = baseScaleVariation * depthScale
            
            val baseAlphaVariation = 0.7f + (index % 4) * 0.08f
            val pieceAlpha = baseAlpha * baseAlphaVariation * depthAlphaMultiplier
            
            // Rotation follows a slower pattern with individual offset
            val pieceRotation = (index * 30f) + rotationTime * 0.3f * (if (index % 2 == 0) 1 else -1)

            Box(
                modifier = Modifier
                    .offset(x = actualX, y = actualY)
                    .rotate(pieceRotation)
                    .scale(pieceScale)
                    .alpha(pieceAlpha)
            ) {
                PuzzlePieceVisualization(
                    piece = piece,
                    cellSize = cellSize,
                )
            }
        }
    }
}
