package com.letit0or1.kawa.playable.core

data class PlayableGameState(
    val puzzle: GamePuzzleGrid,
    val solutionMask: Array<IntArray>,
    val pieces: List<PuzzlePiece>,
    val progress: Float = 0f,
    val selectedPieceIndex: Int? = null,
    val isSolved: Boolean = false,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        other as PlayableGameState
        if (puzzle != other.puzzle) return false
        if (!solutionMask.contentDeepEquals(other.solutionMask)) return false
        if (pieces != other.pieces) return false
        if (progress != other.progress) return false
        if (selectedPieceIndex != other.selectedPieceIndex) return false
        if (isSolved != other.isSolved) return false
        return true
    }

    override fun hashCode(): Int {
        var result = puzzle.hashCode()
        result = 31 * result + solutionMask.contentDeepHashCode()
        result = 31 * result + pieces.hashCode()
        result = 31 * result + progress.hashCode()
        result = 31 * result + (selectedPieceIndex ?: 0)
        result = 31 * result + isSolved.hashCode()
        return result
    }
}

object DemoPuzzles {
    
    // Simple 4x4 puzzle - easy to solve in a few moves
    val simplePuzzle: PuzzleGrid = listOf(
        listOf(CellState.Active, CellState.Active, CellState.Active, CellState.Active),
        listOf(CellState.Active, CellState.Active, CellState.Active, CellState.Active),
        listOf(CellState.Active, CellState.Active, CellState.Active, CellState.Active),
        listOf(CellState.Active, CellState.Active, CellState.Active, CellState.Active),
    )
    
    // Heart-shaped puzzle - visually appealing
    val heartPuzzle: PuzzleGrid = listOf(
        listOf(CellState.Inactive, CellState.Active, CellState.Inactive, CellState.Active, CellState.Inactive),
        listOf(CellState.Active, CellState.Active, CellState.Active, CellState.Active, CellState.Active),
        listOf(CellState.Active, CellState.Active, CellState.Active, CellState.Active, CellState.Active),
        listOf(CellState.Inactive, CellState.Active, CellState.Active, CellState.Active, CellState.Inactive),
        listOf(CellState.Inactive, CellState.Inactive, CellState.Active, CellState.Inactive, CellState.Inactive),
    )
    
    // Cross-shaped puzzle
    val crossPuzzle: PuzzleGrid = listOf(
        listOf(CellState.Inactive, CellState.Active, CellState.Inactive),
        listOf(CellState.Active, CellState.Active, CellState.Active),
        listOf(CellState.Inactive, CellState.Active, CellState.Inactive),
    )
    
    // L-shaped puzzle - simple but interesting
    val lShapePuzzle: PuzzleGrid = listOf(
        listOf(CellState.Active, CellState.Inactive, CellState.Inactive),
        listOf(CellState.Active, CellState.Inactive, CellState.Inactive),
        listOf(CellState.Active, CellState.Active, CellState.Active),
    )
    
    // 5x5 puzzle with obstacle - more challenging
    val puzzleWithObstacle: PuzzleGrid = listOf(
        listOf(CellState.Active, CellState.Active, CellState.Active, CellState.Active, CellState.Active),
        listOf(CellState.Active, CellState.Active, CellState.Obstacle, CellState.Active, CellState.Active),
        listOf(CellState.Active, CellState.Obstacle, CellState.Obstacle, CellState.Obstacle, CellState.Active),
        listOf(CellState.Active, CellState.Active, CellState.Obstacle, CellState.Active, CellState.Active),
        listOf(CellState.Active, CellState.Active, CellState.Active, CellState.Active, CellState.Active),
    )
    
    // Pre-defined pieces for demo (deterministic for ad)
    fun getSimplePuzzlePieces(): List<PuzzlePiece> = listOf(
        // L-piece
        PuzzlePiece(
            grid = listOf(
                listOf(CellState.Active, CellState.Inactive),
                listOf(CellState.Active, CellState.Inactive),
                listOf(CellState.Active, CellState.Active),
            ),
            mask = 1
        ),
        // Square piece
        PuzzlePiece(
            grid = listOf(
                listOf(CellState.Active, CellState.Active),
                listOf(CellState.Active, CellState.Active),
            ),
            mask = 2
        ),
        // Line piece (horizontal)
        PuzzlePiece(
            grid = listOf(
                listOf(CellState.Active, CellState.Active, CellState.Active, CellState.Active),
            ),
            mask = 3
        ),
        // T-piece
        PuzzlePiece(
            grid = listOf(
                listOf(CellState.Active, CellState.Active, CellState.Active),
                listOf(CellState.Inactive, CellState.Active, CellState.Inactive),
            ),
            mask = 4
        ),
        // Small L
        PuzzlePiece(
            grid = listOf(
                listOf(CellState.Active, CellState.Inactive),
                listOf(CellState.Active, CellState.Active),
            ),
            mask = 5
        ),
    )
    
    // Pre-built solution mask for simple puzzle
    fun getSimplePuzzleSolutionMask(): Array<IntArray> = arrayOf(
        intArrayOf(1, 1, 2, 2),
        intArrayOf(1, 3, 2, 2),
        intArrayOf(1, 3, 3, 3),
        intArrayOf(4, 4, 4, 5),
    )
}
