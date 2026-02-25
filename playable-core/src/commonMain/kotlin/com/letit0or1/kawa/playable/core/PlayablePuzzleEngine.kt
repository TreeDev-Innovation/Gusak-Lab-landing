package com.letit0or1.kawa.playable.core

import kotlin.math.min

class PlayablePuzzleEngine {
    
    fun createPuzzleGrid(grid: PuzzleGrid): GamePuzzleGrid {
        return grid.map { row ->
            row.map { cell ->
                when (cell) {
                    CellState.Active -> GamePuzzleCellState.Active
                    CellState.Inactive -> GamePuzzleCellState.Empty
                    CellState.Obstacle -> GamePuzzleCellState.Obstacle
                }
            }
        }
    }
    
    fun splitPuzzle(grid: PuzzleGrid): SplitterResult {
        val carvedGrid = Array(grid.size) { row ->
            IntArray(grid[0].size) { col ->
                if (grid[row][col] == CellState.Active) 0 else -1
            }
        }

        var currentPieceNumber = 1
        for (row in grid.indices) {
            for (col in grid[row].indices) {
                if (carvedGrid[row][col] == 0) {
                    carvedGrid[row][col] = currentPieceNumber
                    carvePiece(carvedGrid, row, col, currentPieceNumber, grid.size)
                    currentPieceNumber++
                }
            }
        }

        val pieces = (1 until currentPieceNumber).map { pieceNumber ->
            val pieceGrid = carvedGrid.map { row ->
                row.map { cell ->
                    if (cell == pieceNumber) CellState.Active else CellState.Inactive
                }
            }.optimizePuzzle()

            PuzzlePiece(
                grid = pieceGrid,
                mask = pieceNumber,
            )
        }
        
        return SplitterResult(
            grid = grid,
            pieces = pieces,
            solutionMask = carvedGrid
        )
    }
    
    private fun getMaxPieceSize(): Int = listOf(2, 3, 4, 4, 4, 4, 4, 4, 4, 5).random()
    
    private fun carvePiece(
        carvedGrid: Array<IntArray>,
        row: Int,
        col: Int,
        piece: Int,
        gridSize: Int
    ) {
        val maxSize = min(getMaxPieceSize(), gridSize)
        var currentPointer = row to col
        for (i in 1 until maxSize) {
            findEmptyNeighbor(carvedGrid, currentPointer.first, currentPointer.second)?.let {
                carvedGrid[it.first][it.second] = piece
                currentPointer = it
            } ?: return
        }
    }

    private fun findEmptyNeighbor(
        carvedGrid: Array<IntArray>,
        row: Int,
        col: Int,
    ): Pair<Int, Int>? {
        val sides = Side.entries.toMutableList()
        while (sides.isNotEmpty()) {
            val side = sides.random()
            sides.remove(side)

            val (newRow, newCol) = when (side) {
                Side.Top -> row - 1 to col
                Side.Bottom -> row + 1 to col
                Side.Left -> row to col - 1
                Side.Right -> row to col + 1
            }
            
            val value = carvedGrid.getOrNull(newRow)?.getOrNull(newCol) ?: -1
            if (value == 0) {
                return newRow to newCol
            }
        }
        return null
    }
    
    private fun List<List<CellState>>.optimizePuzzle(): List<List<CellState>> {
        if (isEmpty()) return this
        
        var result = this
        
        // Remove empty rows from top
        while (result.isNotEmpty() && result.first().all { it == CellState.Inactive }) {
            result = result.drop(1)
        }
        // Remove empty rows from bottom
        while (result.isNotEmpty() && result.last().all { it == CellState.Inactive }) {
            result = result.dropLast(1)
        }
        
        if (result.isEmpty()) return result
        
        // Remove empty columns from left
        while (result.first().isNotEmpty() && result.all { it.first() == CellState.Inactive }) {
            result = result.map { it.drop(1) }
        }
        // Remove empty columns from right
        while (result.first().isNotEmpty() && result.all { it.last() == CellState.Inactive }) {
            result = result.map { it.dropLast(1) }
        }
        
        return result
    }
    
    fun canPlacePiece(
        grid: GamePuzzleGrid,
        piece: PuzzlePiece,
        startRow: Int,
        startCol: Int
    ): Boolean {
        val activeCells = piece.getActiveCells()
        return activeCells.all { (pieceRow, pieceCol) ->
            val gridRow = startRow + pieceRow
            val gridCol = startCol + pieceCol
            val cell = grid.getOrNull(gridRow)?.getOrNull(gridCol)
            cell == GamePuzzleCellState.Active
        }
    }
    
    fun placePiece(
        grid: GamePuzzleGrid,
        piece: PuzzlePiece,
        startRow: Int,
        startCol: Int
    ): GamePuzzleGrid {
        val mutableGrid = grid.mutableCopy()
        val activeCells = piece.getActiveCells()
        
        activeCells.forEach { (pieceRow, pieceCol) ->
            val gridRow = startRow + pieceRow
            val gridCol = startCol + pieceCol
            mutableGrid[gridRow][gridCol] = GamePuzzleCellState.Filled(piece)
        }
        
        return mutableGrid
    }
    
    fun applyHover(
        grid: GamePuzzleGrid,
        piece: PuzzlePiece,
        startRow: Int,
        startCol: Int,
        isValid: Boolean
    ): GamePuzzleGrid {
        val mutableGrid = grid.mutableCopy()
        val activeCells = piece.getActiveCells()
        
        activeCells.forEach { (pieceRow, pieceCol) ->
            val gridRow = startRow + pieceRow
            val gridCol = startCol + pieceCol
            if (gridRow in mutableGrid.indices && gridCol in mutableGrid[gridRow].indices) {
                val currentCell = mutableGrid[gridRow][gridCol]
                if (currentCell == GamePuzzleCellState.Active) {
                    mutableGrid[gridRow][gridCol] = if (isValid) {
                        GamePuzzleCellState.Hover(piece.mask)
                    } else {
                        GamePuzzleCellState.InvalidHover(piece.mask)
                    }
                }
            }
        }
        
        return mutableGrid
    }
    
    fun clearHover(grid: GamePuzzleGrid): GamePuzzleGrid {
        return grid.map { row ->
            row.map { cell ->
                when (cell) {
                    is GamePuzzleCellState.Hover -> GamePuzzleCellState.Active
                    is GamePuzzleCellState.InvalidHover -> GamePuzzleCellState.Active
                    else -> cell
                }
            }
        }
    }
    
    fun calculateProgress(grid: GamePuzzleGrid): Float {
        var totalActive = 0
        var filled = 0
        
        grid.forEach { row ->
            row.forEach { cell ->
                when (cell) {
                    is GamePuzzleCellState.Active -> totalActive++
                    is GamePuzzleCellState.Filled -> filled++
                    else -> {}
                }
            }
        }
        
        val total = totalActive + filled
        return if (total > 0) filled.toFloat() / total else 0f
    }
}

data class SplitterResult(
    val grid: PuzzleGrid,
    val pieces: List<PuzzlePiece>,
    val solutionMask: Array<IntArray>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        other as SplitterResult
        if (grid != other.grid) return false
        if (pieces != other.pieces) return false
        if (!solutionMask.contentDeepEquals(other.solutionMask)) return false
        return true
    }

    override fun hashCode(): Int {
        var result = grid.hashCode()
        result = 31 * result + pieces.hashCode()
        result = 31 * result + solutionMask.contentDeepHashCode()
        return result
    }
}
