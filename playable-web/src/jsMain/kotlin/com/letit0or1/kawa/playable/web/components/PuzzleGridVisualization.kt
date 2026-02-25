package com.letit0or1.kawa.playable.web.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.letit0or1.kawa.playable.core.GamePuzzleCellState
import com.letit0or1.kawa.playable.core.GamePuzzleGrid
import com.letit0or1.kawa.playable.core.Side

@Composable
fun PuzzleGridVisualization(
    modifier: Modifier = Modifier,
    grid: GamePuzzleGrid,
    cellSize: Dp = 40.dp,
) {
    Column(modifier = modifier) {
        grid.forEachIndexed { rowIndex, row ->
            Row {
                row.forEachIndexed { colIndex, cellState ->
                    val neighbours = mutableSetOf<Side>()
                    val currentMask = grid.getFilledMask(rowIndex, colIndex)
                    if (currentMask != null) {
                        if (grid.getFilledMask(rowIndex - 1, colIndex) == currentMask) {
                            neighbours.add(Side.Top)
                        }
                        if (grid.getFilledMask(rowIndex + 1, colIndex) == currentMask) {
                            neighbours.add(Side.Bottom)
                        }
                        if (grid.getFilledMask(rowIndex, colIndex - 1) == currentMask) {
                            neighbours.add(Side.Left)
                        }
                        if (grid.getFilledMask(rowIndex, colIndex + 1) == currentMask) {
                            neighbours.add(Side.Right)
                        }
                    }

                    GameCellBox(
                        cellSize = cellSize,
                        state = cellState,
                        neighbours = neighbours
                    )
                }
            }
        }
    }
}

private fun GamePuzzleGrid.getFilledMask(x: Int, y: Int): Int? {
    val cellState = this.getOrNull(x)?.getOrNull(y)
    return when (cellState) {
        is GamePuzzleCellState.Filled -> cellState.puzzlePiece.mask
        is GamePuzzleCellState.Hover -> cellState.mask
        is GamePuzzleCellState.InvalidHover -> cellState.mask
        else -> null
    }
}
