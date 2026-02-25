package com.letit0or1.kawa.playable.web.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.letit0or1.kawa.playable.core.CellState
import com.letit0or1.kawa.playable.core.GamePuzzleCellState
import com.letit0or1.kawa.playable.core.PuzzlePiece
import com.letit0or1.kawa.playable.core.Side

@Composable
fun PuzzlePieceVisualization(
    modifier: Modifier = Modifier,
    piece: PuzzlePiece,
    cellSize: Dp = 20.dp,
) {
    Column(modifier) {
        piece.grid.forEachIndexed { rowIndex, row ->
            Row {
                row.forEachIndexed { colIndex, cellState ->
                    val neighbours = mutableSetOf<Side>()
                    if (cellState == CellState.Active) {
                        if (piece.fits(rowIndex - 1, colIndex)) {
                            neighbours.add(Side.Top)
                        }
                        if (piece.fits(rowIndex + 1, colIndex)) {
                            neighbours.add(Side.Bottom)
                        }
                        if (piece.fits(rowIndex, colIndex - 1)) {
                            neighbours.add(Side.Left)
                        }
                        if (piece.fits(rowIndex, colIndex + 1)) {
                            neighbours.add(Side.Right)
                        }
                    }

                    GameCellBox(
                        modifier = Modifier,
                        cellSize = cellSize,
                        state = when (cellState) {
                            CellState.Active -> GamePuzzleCellState.Filled(piece)
                            CellState.Inactive -> GamePuzzleCellState.Empty
                            CellState.Obstacle -> GamePuzzleCellState.Obstacle
                        },
                        neighbours = neighbours,
                    )
                }
            }
        }
    }
}
