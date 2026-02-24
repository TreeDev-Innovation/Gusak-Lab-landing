package com.letit0or1.kawa.playable.web.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.letit0or1.kawa.playable.core.GamePuzzleCellState
import com.letit0or1.kawa.playable.core.Side
import com.letit0or1.kawa.playable.web.theme.transparent

@Composable
fun GameCellBox(
    modifier: Modifier = Modifier,
    cellSize: Dp = 20.dp,
    state: GamePuzzleCellState,
    neighbours: Set<Side> = emptySet()
) {
    val targetBackgroundColor = when (state) {
        is GamePuzzleCellState.Active -> MaterialTheme.colorScheme.secondaryContainer
        is GamePuzzleCellState.Filled -> MaterialTheme.colorScheme.onPrimaryContainer
        is GamePuzzleCellState.Hover -> MaterialTheme.colorScheme.primary
        is GamePuzzleCellState.InvalidHover -> MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
        GamePuzzleCellState.Empty -> MaterialTheme.colorScheme.transparent
        GamePuzzleCellState.Obstacle -> MaterialTheme.colorScheme.errorContainer
    }
    val targetBorderColor = when (state) {
        is GamePuzzleCellState.Active -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f)
        is GamePuzzleCellState.Filled -> MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
        is GamePuzzleCellState.Hover -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)
        is GamePuzzleCellState.InvalidHover -> MaterialTheme.colorScheme.error.copy(alpha = 0.4f)
        GamePuzzleCellState.Empty -> MaterialTheme.colorScheme.transparent
        GamePuzzleCellState.Obstacle -> MaterialTheme.colorScheme.error.copy(alpha = 0.2f)
    }

    val animatedBackgroundColor by animateColorAsState(
        targetValue = targetBackgroundColor,
        animationSpec = tween(durationMillis = 150),
        label = "cellBackgroundColor"
    )
    val animatedBorderColor by animateColorAsState(
        targetValue = targetBorderColor,
        animationSpec = tween(durationMillis = 150),
        label = "cellBorderColor"
    )

    val radius = 25
    val shape = RoundedCornerShape(
        topStart = if (neighbours.contains(Side.Top) || neighbours.contains(Side.Left))
            CornerSize(0) else CornerSize(radius),
        topEnd = if (neighbours.contains(Side.Top) || neighbours.contains(Side.Right))
            CornerSize(0) else CornerSize(radius),
        bottomStart = if (neighbours.contains(Side.Bottom) || neighbours.contains(Side.Left))
            CornerSize(0) else CornerSize(radius),
        bottomEnd = if (neighbours.contains(Side.Bottom) || neighbours.contains(Side.Right))
            CornerSize(0) else CornerSize(radius),
    )

    Box(
        modifier = modifier
            .size(cellSize)
            .background(color = animatedBackgroundColor, shape = shape)
            .border(color = animatedBorderColor, width = cellSize / 20, shape = shape),
        contentAlignment = Alignment.Center,
    ) {}
}
