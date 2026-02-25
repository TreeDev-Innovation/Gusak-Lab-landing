package com.letit0or1.kawa.playable.core

sealed class GamePuzzleCellState {
    data object Active : GamePuzzleCellState()
    data class Hover(val mask: Int) : GamePuzzleCellState()
    data class InvalidHover(val mask: Int) : GamePuzzleCellState()
    data class Filled(val puzzlePiece: PuzzlePiece) : GamePuzzleCellState()
    data object Empty : GamePuzzleCellState()
    data object Obstacle : GamePuzzleCellState()
}

typealias GamePuzzleGrid = List<List<GamePuzzleCellState>>

val GamePuzzleGrid.cols: Int get() = firstOrNull()?.size ?: 0
val GamePuzzleGrid.rows: Int get() = size

fun GamePuzzleGrid.mutableCopy(): MutableList<MutableList<GamePuzzleCellState>> {
    return this.map { row -> row.toMutableList() }.toMutableList()
}
