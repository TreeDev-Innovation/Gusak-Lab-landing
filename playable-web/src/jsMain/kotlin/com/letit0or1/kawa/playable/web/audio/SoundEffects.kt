package com.letit0or1.kawa.playable.web.audio

import kotlinx.browser.window

/**
 * Simple sound effects using Web Audio API.
 * All sounds are procedurally generated - no external files needed.
 */
object SoundEffects {
    
    private var audioContext: dynamic = null
    private var isInitialized = false
    
    /**
     * Initialize audio context. Must be called after user interaction
     * (browsers require user gesture to enable audio).
     */
    fun initialize() {
        if (isInitialized) return
        
        try {
            val AudioContext = js("window.AudioContext || window.webkitAudioContext")
            audioContext = js("new AudioContext()")
            isInitialized = true
        } catch (e: Exception) {
            console.log("Failed to initialize audio: ${e.message}")
        }
    }
    
    /**
     * Play a soft "pop" sound when a piece is placed successfully.
     */
    fun playPlaceSound() {
        if (!isInitialized) initialize()
        if (audioContext == null) return
        
        try {
            val ctx = audioContext
            val oscillator = ctx.createOscillator()
            val gainNode = ctx.createGain()
            
            oscillator.connect(gainNode)
            gainNode.connect(ctx.destination)
            
            // Soft pop sound - starts at higher frequency and drops
            oscillator.type = "sine"
            oscillator.frequency.setValueAtTime(600, ctx.currentTime)
            oscillator.frequency.exponentialRampToValueAtTime(200, ctx.currentTime + 0.1)
            
            // Quick fade out
            gainNode.gain.setValueAtTime(0.3, ctx.currentTime)
            gainNode.gain.exponentialRampToValueAtTime(0.01, ctx.currentTime + 0.15)
            
            oscillator.start(ctx.currentTime)
            oscillator.stop(ctx.currentTime + 0.15)
        } catch (e: Exception) {
            console.log("Error playing place sound: ${e.message}")
        }
    }
    
    /**
     * Play a cheerful "success" sound when puzzle is completed.
     */
    fun playSuccessSound() {
        if (!isInitialized) initialize()
        if (audioContext == null) return
        
        try {
            val ctx = audioContext
            
            // Play a simple ascending arpeggio (C-E-G-C)
            val notes = listOf(523.25, 659.25, 783.99, 1046.50) // C5, E5, G5, C6
            val noteDuration = 0.12
            
            notes.forEachIndexed { index, frequency ->
                val oscillator = ctx.createOscillator()
                val gainNode = ctx.createGain()
                
                oscillator.connect(gainNode)
                gainNode.connect(ctx.destination)
                
                oscillator.type = "sine"
                val startTime = ctx.currentTime + index * noteDuration
                
                oscillator.frequency.setValueAtTime(frequency, startTime)
                
                // Envelope
                gainNode.gain.setValueAtTime(0.0, startTime)
                gainNode.gain.linearRampToValueAtTime(0.25, startTime + 0.02)
                gainNode.gain.exponentialRampToValueAtTime(0.01, startTime + noteDuration + 0.1)
                
                oscillator.start(startTime)
                oscillator.stop(startTime + noteDuration + 0.15)
            }
        } catch (e: Exception) {
            console.log("Error playing success sound: ${e.message}")
        }
    }
    
    /**
     * Play a subtle "hover" sound when piece hovers over valid position.
     */
    fun playHoverSound() {
        if (!isInitialized) initialize()
        if (audioContext == null) return
        
        try {
            val ctx = audioContext
            val oscillator = ctx.createOscillator()
            val gainNode = ctx.createGain()
            
            oscillator.connect(gainNode)
            gainNode.connect(ctx.destination)
            
            // Very subtle tick
            oscillator.type = "sine"
            oscillator.frequency.setValueAtTime(800, ctx.currentTime)
            
            gainNode.gain.setValueAtTime(0.08, ctx.currentTime)
            gainNode.gain.exponentialRampToValueAtTime(0.01, ctx.currentTime + 0.05)
            
            oscillator.start(ctx.currentTime)
            oscillator.stop(ctx.currentTime + 0.05)
        } catch (e: Exception) {
            // Silently ignore hover sound errors
        }
    }
    
    /**
     * Play a "pickup" sound when user starts dragging a piece.
     */
    fun playPickupSound() {
        if (!isInitialized) initialize()
        if (audioContext == null) return
        
        try {
            val ctx = audioContext
            val oscillator = ctx.createOscillator()
            val gainNode = ctx.createGain()
            
            oscillator.connect(gainNode)
            gainNode.connect(ctx.destination)
            
            // Rising tone
            oscillator.type = "sine"
            oscillator.frequency.setValueAtTime(300, ctx.currentTime)
            oscillator.frequency.exponentialRampToValueAtTime(500, ctx.currentTime + 0.08)
            
            gainNode.gain.setValueAtTime(0.15, ctx.currentTime)
            gainNode.gain.exponentialRampToValueAtTime(0.01, ctx.currentTime + 0.1)
            
            oscillator.start(ctx.currentTime)
            oscillator.stop(ctx.currentTime + 0.1)
        } catch (e: Exception) {
            console.log("Error playing pickup sound: ${e.message}")
        }
    }
}

private external val console: Console

private external interface Console {
    fun log(message: String)
}
