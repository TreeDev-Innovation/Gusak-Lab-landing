package com.letit0or1.kawa.playable.web

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.letit0or1.kawa.playable.core.*
import com.letit0or1.kawa.playable.web.audio.SoundEffects
import com.letit0or1.kawa.playable.web.components.FloatingPiecesBackground
import com.letit0or1.kawa.playable.web.components.PuzzleGridVisualization
import com.letit0or1.kawa.playable.web.components.PuzzlePieceVisualization
import com.letit0or1.kawa.playable.web.theme.PlayableTheme
import kotlin.math.roundToInt

// Drag state for piece being dragged
private data class DragState(
    val pieceIndex: Int,
    val piece: PuzzlePiece,
    val offset: Offset,
    val startPosition: Offset,
    val isDragging: Boolean = false,
)

@Composable
fun PlayableAdScreen() {
    val engine = remember { PlayablePuzzleEngine() }
    val density = LocalDensity.current
    
    // Use deterministic puzzle for ad
    val initialPuzzle = remember { DemoPuzzles.simplePuzzle }
    val splitterResult = remember { engine.splitPuzzle(initialPuzzle) }
    
    var gameState by remember {
        mutableStateOf(
            PlayableGameState(
                puzzle = engine.createPuzzleGrid(initialPuzzle),
                solutionMask = splitterResult.solutionMask,
                pieces = splitterResult.pieces.shuffled(),
                progress = 0f,
                selectedPieceIndex = null,
                isSolved = false,
            )
        )
    }
    
    var showCTA by remember { mutableStateOf(false) }
    var moveCount by remember { mutableStateOf(0) }
    var dragState by remember { mutableStateOf<DragState?>(null) }
    var puzzleGridPosition by remember { mutableStateOf(Offset.Zero) }
    
    // Show CTA after solving or after 3 moves
    LaunchedEffect(gameState.isSolved, moveCount) {
        if (gameState.isSolved || moveCount >= 3) {
            showCTA = true
        }
    }
    
    PlayableTheme {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Subtle floating pieces background
            FloatingPiecesBackground(
                modifier = Modifier.fillMaxSize(),
                pieceCount = 15,
                cellSize = 12.dp,
                baseAlpha = 0.15f, // More visible for testing
            )
            
            val puzzleRows = gameState.puzzle.size
            val puzzleCols = gameState.puzzle.firstOrNull()?.size ?: 1
            
            // Calculate cell size based on available space
            val availableWidth = maxWidth - 32.dp
            val availableHeight = maxHeight - 280.dp
            
            val cellSizeFromWidth = availableWidth / puzzleCols
            val cellSizeFromHeight = availableHeight / puzzleRows
            val cellSize = minOf(cellSizeFromWidth, cellSizeFromHeight, 60.dp)
            
            // Deck cell size is smaller (pieces grow when dragged)
            val deckCellSize = cellSize * 0.45f
            
            // Animated cell size for dragged piece
            val draggedPieceCellSize by animateDpAsState(
                targetValue = if (dragState?.isDragging == true) cellSize else deckCellSize,
                animationSpec = spring(stiffness = Spring.StiffnessMedium),
                label = "draggedPieceCellSize"
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // Header with hook text
                HookHeader(
                    progress = gameState.progress,
                    moveCount = moveCount,
                )
                
                Spacer(modifier = Modifier.weight(1f))
                
                // Puzzle Grid
                Box(
                    modifier = Modifier
                        .onGloballyPositioned { coords ->
                            puzzleGridPosition = coords.positionInRoot()
                        }
                ) {
                    val displayGrid by remember {
                        derivedStateOf {
                            val currentDragState = dragState
                            if (currentDragState != null && currentDragState.isDragging) {
                                val cellSizePx = with(density) { cellSize.toPx() }
                                val dragOffset = currentDragState.offset
                                
                                // The dragged piece is centered on cursor, so we need to calculate
                                // where the top-left corner of the piece would be
                                val pieceWidthPx = cellSizePx * currentDragState.piece.cols
                                val pieceHeightPx = cellSizePx * currentDragState.piece.rows
                                val pieceTopLeftX = dragOffset.x - pieceWidthPx / 2
                                val pieceTopLeftY = dragOffset.y - pieceHeightPx / 2
                                
                                val relativeX = pieceTopLeftX - puzzleGridPosition.x
                                val relativeY = pieceTopLeftY - puzzleGridPosition.y
                                
                                // Add half cell to snap to nearest cell (not floor)
                                val col = ((relativeX + cellSizePx / 2) / cellSizePx).toInt()
                                val row = ((relativeY + cellSizePx / 2) / cellSizePx).toInt()
                                
                                if (row >= 0 && col >= 0) {
                                    val canPlace = engine.canPlacePiece(gameState.puzzle, currentDragState.piece, row, col)
                                    engine.applyHover(gameState.puzzle, currentDragState.piece, row, col, canPlace)
                                } else {
                                    gameState.puzzle
                                }
                            } else {
                                engine.clearHover(gameState.puzzle)
                            }
                        }
                    }
                    
                    PuzzleGridVisualization(
                        grid = displayGrid,
                        cellSize = cellSize,
                    )
                }
                
                Spacer(modifier = Modifier.weight(1f))
                
                // Piece Deck with drag support
                if (gameState.pieces.isNotEmpty()) {
                    DraggablePieceDeck(
                        pieces = gameState.pieces,
                        deckCellSize = deckCellSize,
                        draggedIndex = dragState?.pieceIndex,
                        onDragStart = { index, piece, startPos ->
                            SoundEffects.playPickupSound()
                            dragState = DragState(
                                pieceIndex = index,
                                piece = piece,
                                offset = startPos,
                                startPosition = startPos,
                                isDragging = true,
                            )
                        },
                        onDrag = { offset ->
                            dragState = dragState?.copy(offset = offset)
                        },
                        onDragEnd = {
                            val currentDrag = dragState
                            if (currentDrag != null) {
                                val cellSizePx = with(density) { cellSize.toPx() }
                                
                                // Calculate top-left corner of centered piece
                                val pieceWidthPx = cellSizePx * currentDrag.piece.cols
                                val pieceHeightPx = cellSizePx * currentDrag.piece.rows
                                val pieceTopLeftX = currentDrag.offset.x - pieceWidthPx / 2
                                val pieceTopLeftY = currentDrag.offset.y - pieceHeightPx / 2
                                
                                val relativeX = pieceTopLeftX - puzzleGridPosition.x
                                val relativeY = pieceTopLeftY - puzzleGridPosition.y
                                
                                // Snap to nearest cell
                                val col = ((relativeX + cellSizePx / 2) / cellSizePx).toInt()
                                val row = ((relativeY + cellSizePx / 2) / cellSizePx).toInt()
                                
                                if (row >= 0 && col >= 0 && 
                                    engine.canPlacePiece(gameState.puzzle, currentDrag.piece, row, col)) {
                                    val newPuzzle = engine.placePiece(gameState.puzzle, currentDrag.piece, row, col)
                                    val newPieces = gameState.pieces.toMutableList().apply {
                                        removeAt(currentDrag.pieceIndex)
                                    }
                                    val newProgress = engine.calculateProgress(newPuzzle)
                                    val isSolved = newProgress >= 1f
                                    
                                    // Play sound effects
                                    if (isSolved) {
                                        SoundEffects.playSuccessSound()
                                    } else {
                                        SoundEffects.playPlaceSound()
                                    }
                                    
                                    gameState = gameState.copy(
                                        puzzle = newPuzzle,
                                        pieces = newPieces,
                                        progress = newProgress,
                                        isSolved = isSolved,
                                    )
                                    moveCount++
                                }
                            }
                            dragState = null
                        }
                    )
                }
            }
            
            // Dragged piece overlay (rendered at root level for proper z-index)
            val currentDragForOverlay = dragState
            if (currentDragForOverlay != null && currentDragForOverlay.isDragging) {
                Box(
                    modifier = Modifier
                        .zIndex(100f)
                        .offset {
                            IntOffset(
                                (currentDragForOverlay.offset.x - with(density) { draggedPieceCellSize.toPx() } * currentDragForOverlay.piece.cols / 2).roundToInt(),
                                (currentDragForOverlay.offset.y - with(density) { draggedPieceCellSize.toPx() } * currentDragForOverlay.piece.rows / 2).roundToInt()
                            )
                        }
                        .graphicsLayer {
                            alpha = 0.9f
                            shadowElevation = 8f
                        }
                ) {
                    PuzzlePieceVisualization(
                        piece = currentDragForOverlay.piece,
                        cellSize = draggedPieceCellSize,
                    )
                }
            }
            
            // CTA Overlay - needs higher zIndex than dragged piece
            if (showCTA) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .zIndex(200f)
                ) {
                    CTAOverlay(
                        isSolved = gameState.isSolved,
                        onPlayNow = {
                            console.log("Download button clicked!")
                            openAppStore()
                        },
                        onContinue = {
                            console.log("Continue button clicked!")
                            showCTA = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun HookHeader(
    progress: Float,
    moveCount: Int,
) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(300),
        label = "progress"
    )
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = if (progress >= 1f) "🎉 Solved!" else "Can you solve this?",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // Progress bar
        LinearProgressIndicator(
            progress = { animatedProgress },
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceContainer,
        )
        
        Spacer(modifier = Modifier.height(4.dp))
        
        Text(
            text = "${(progress * 100).toInt()}% complete",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
        )
    }
}

@Composable
private fun InteractivePuzzleGrid(
    gameState: PlayableGameState,
    cellSize: Dp,
    engine: PlayablePuzzleEngine,
    onPiecePlaced: (PlayableGameState) -> Unit,
) {
    var hoveredCell by remember { mutableStateOf<Pair<Int, Int>?>(null) }
    var displayGrid by remember(gameState.puzzle) { mutableStateOf(gameState.puzzle) }
    
    // Update hover visualization
    LaunchedEffect(hoveredCell, gameState.selectedPieceIndex, gameState.puzzle) {
        val selectedIndex = gameState.selectedPieceIndex
        if (selectedIndex != null && hoveredCell != null) {
            val piece = gameState.pieces.getOrNull(selectedIndex)
            if (piece != null) {
                val (row, col) = hoveredCell!!
                val canPlace = engine.canPlacePiece(gameState.puzzle, piece, row, col)
                displayGrid = engine.applyHover(gameState.puzzle, piece, row, col, canPlace)
            }
        } else {
            displayGrid = engine.clearHover(gameState.puzzle)
        }
    }
    
    Box(
        modifier = Modifier
            .pointerInput(gameState.selectedPieceIndex, gameState.pieces) {
                awaitPointerEventScope {
                    while (true) {
                        val event = awaitPointerEvent()
                        val position = event.changes.firstOrNull()?.position
                        
                        if (position != null) {
                            val cellSizePx = cellSize.toPx()
                            val col = (position.x / cellSizePx).toInt()
                            val row = (position.y / cellSizePx).toInt()
                            
                            val gridRows = gameState.puzzle.size
                            val gridCols = gameState.puzzle.firstOrNull()?.size ?: 0
                            
                            if (row in 0 until gridRows && col in 0 until gridCols) {
                                when (event.type) {
                                    PointerEventType.Move -> {
                                        hoveredCell = row to col
                                    }
                                    PointerEventType.Press -> {
                                        val selectedIndex = gameState.selectedPieceIndex
                                        if (selectedIndex != null) {
                                            val piece = gameState.pieces.getOrNull(selectedIndex)
                                            if (piece != null && engine.canPlacePiece(gameState.puzzle, piece, row, col)) {
                                                val newPuzzle = engine.placePiece(gameState.puzzle, piece, row, col)
                                                val newPieces = gameState.pieces.toMutableList().apply {
                                                    removeAt(selectedIndex)
                                                }
                                                val newProgress = engine.calculateProgress(newPuzzle)
                                                onPiecePlaced(
                                                    gameState.copy(
                                                        puzzle = newPuzzle,
                                                        pieces = newPieces,
                                                        progress = newProgress,
                                                        selectedPieceIndex = null,
                                                        isSolved = newProgress >= 1f,
                                                    )
                                                )
                                            }
                                        }
                                    }
                                    PointerEventType.Exit -> {
                                        hoveredCell = null
                                    }
                                }
                            } else {
                                hoveredCell = null
                            }
                        }
                    }
                }
            },
        contentAlignment = Alignment.Center,
    ) {
        PuzzleGridVisualization(
            grid = displayGrid,
            cellSize = cellSize,
        )
    }
}

@Composable
private fun CTAOverlay(
    isSolved: Boolean,
    onPlayNow: () -> Unit,
    onContinue: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.8f)),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(32.dp),
        ) {
            Text(
                text = if (isSolved) "🎉 Amazing!" else "You're doing great!",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = if (isSolved) 
                    "Ready for more challenging puzzles?" 
                else 
                    "Download the full game for 100+ puzzles!",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White.copy(alpha = 0.9f),
                textAlign = TextAlign.Center,
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Primary CTA Button - using clickable for better desktop compatibility
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(56.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = LocalIndication.current,
                        onClick = onPlayNow
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "DOWNLOAD NOW",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
            
            if (!isSolved) {
                Spacer(modifier = Modifier.height(16.dp))
                
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = LocalIndication.current,
                            onClick = onContinue
                        )
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "Continue Playing",
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 16.sp,
                    )
                }
            }
        }
    }
}

@Composable
private fun DraggablePieceDeck(
    pieces: List<PuzzlePiece>,
    deckCellSize: Dp,
    draggedIndex: Int?,
    onDragStart: (Int, PuzzlePiece, Offset) -> Unit,
    onDrag: (Offset) -> Unit,
    onDragEnd: () -> Unit,
) {
    val highlightColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
    val highlightBorderColor = MaterialTheme.colorScheme.primary

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.surfaceContainer,
                RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            )
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        pieces.take(3).forEachIndexed { index, piece ->
            val isDragged = draggedIndex == index
            var slotPosition by remember { mutableStateOf(Offset.Zero) }
            var currentDragOffset by remember { mutableStateOf(Offset.Zero) }
            
            Box(
                modifier = Modifier
                    .onGloballyPositioned { coords ->
                        slotPosition = coords.positionInRoot()
                    }
                    .clip(RoundedCornerShape(8.dp))
                    .background(highlightColor.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                    .border(1.dp, highlightBorderColor.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                    .pointerInput(index, piece) {
                        detectDragGestures(
                            onDragStart = { offset ->
                                currentDragOffset = Offset.Zero
                                val posInRoot = slotPosition + offset
                                onDragStart(index, piece, posInRoot)
                            },
                            onDrag = { change, dragAmount ->
                                change.consume()
                                currentDragOffset += dragAmount
                                val posInRoot = slotPosition + change.position
                                onDrag(posInRoot)
                            },
                            onDragEnd = {
                                currentDragOffset = Offset.Zero
                                onDragEnd()
                            },
                            onDragCancel = {
                                currentDragOffset = Offset.Zero
                                onDragEnd()
                            }
                        )
                    }
                    .padding(8.dp)
                    .graphicsLayer {
                        alpha = if (isDragged) 0f else 1f
                    },
                contentAlignment = Alignment.Center,
            ) {
                PuzzlePieceVisualization(
                    piece = piece,
                    cellSize = deckCellSize,
                )
            }
        }
    }
}

private external val console: Console

private external interface Console {
    fun log(message: String)
}

/**
 * Відкриває відповідний App Store залежно від платформи користувача.
 * 
 * Визначає платформу за User Agent:
 * - Android -> Google Play Store
 * - iOS (iPhone/iPad/iPod) -> Apple App Store
 * - Desktop -> Google Play Store (за замовчуванням)
 * 
 * Щоб змінити посилання на стори, відредагуйте константи нижче.
 */
private fun openAppStore() {
    try {
        val userAgent = kotlinx.browser.window.navigator.userAgent.lowercase()
        
        // TODO: Замініть на реальні посилання на ваші додатки
        val googlePlayUrl = "https://play.google.com/store/apps/details?id=com.letit0or1.kawa"
        val appStoreUrl = "https://apps.apple.com/app/id123456789"
        
        val storeUrl = when {
            userAgent.contains("android") -> {
                console.log("Detected Android device, opening Google Play")
                googlePlayUrl
            }
            userAgent.contains("iphone") || userAgent.contains("ipad") || userAgent.contains("ipod") -> {
                console.log("Detected iOS device, opening App Store")
                appStoreUrl
            }
            else -> {
                console.log("Desktop/unknown platform, opening Google Play")
                googlePlayUrl
            }
        }
        
        console.log("Opening URL: $storeUrl")
        // Use location.href instead of window.open to avoid popup blockers
        kotlinx.browser.window.location.href = storeUrl
    } catch (e: Exception) {
        console.log("Error opening store: ${e.message}")
    }
}
