package com.letit0or1.kawa.playable.core

data class PuzzlePiece(
    val grid: PuzzleGrid,
    val mask: Int,
) {
    val rows: Int get() = grid.size
    val cols: Int get() = grid.firstOrNull()?.size ?: 0
    
    fun fits(row: Int, col: Int): Boolean =
        grid.getOrNull(row)?.getOrNull(col) == CellState.Active
    
    fun getActiveCells(): List<Pair<Int, Int>> {
        val cells = mutableListOf<Pair<Int, Int>>()
        grid.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { colIndex, cell ->
                if (cell == CellState.Active) {
                    cells.add(rowIndex to colIndex)
                }
            }
        }
        return cells
    }
}
