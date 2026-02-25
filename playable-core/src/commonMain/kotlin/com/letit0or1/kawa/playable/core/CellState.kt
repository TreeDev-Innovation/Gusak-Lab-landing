package com.letit0or1.kawa.playable.core

enum class CellState { Active, Inactive, Obstacle }

typealias PuzzleGrid = List<List<CellState>>

fun PuzzleGrid.mutable(): MutableList<MutableList<CellState>> {
    return this.map { row -> row.toMutableList() }.toMutableList()
}
