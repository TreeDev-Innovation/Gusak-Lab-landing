"use client";

import { motion, useAnimationFrame } from "framer-motion";
import Image from "next/image";
import { Mail, Apple, Play, Gamepad2, Sparkles, Users } from "lucide-react";
import { STORE_LINKS } from "./config/store-links";
import { useRef, useState, useEffect } from "react";

// Puzzle piece shapes (matching the game)
const PIECE_SHAPES = [
  // L-shape
  [[1, 0], [1, 0], [1, 1]],
  // T-shape
  [[1, 1, 1], [0, 1, 0]],
  // Square
  [[1, 1], [1, 1]],
  // Line
  [[1, 1, 1]],
  // Z-shape
  [[1, 1, 0], [0, 1, 1]],
  // S-shape
  [[0, 1, 1], [1, 1, 0]],
];

// Base positions for pieces (relative 0-1)
const BASE_POSITIONS = [
  [0.12, 0.12], [0.10, 0.40], [0.08, 0.68], [0.14, 0.88],
  [0.30, 0.20], [0.32, 0.52], [0.28, 0.82],
  [0.50, 0.15], [0.48, 0.48], [0.52, 0.78],
  [0.70, 0.22], [0.72, 0.55], [0.68, 0.85],
  [0.88, 0.18], [0.90, 0.50], [0.86, 0.80],
];

// Puzzle piece component
function PuzzlePiece({ shape, cellSize = 10 }: { shape: number[][], cellSize?: number }) {
  return (
    <div className="flex flex-col">
      {shape.map((row, rowIdx) => (
        <div key={rowIdx} className="flex">
          {row.map((cell, colIdx) => {
            if (cell === 0) return <div key={colIdx} style={{ width: cellSize, height: cellSize }} />;
            
            // Calculate rounded corners based on neighbors
            const hasTop = rowIdx > 0 && shape[rowIdx - 1]?.[colIdx] === 1;
            const hasBottom = rowIdx < shape.length - 1 && shape[rowIdx + 1]?.[colIdx] === 1;
            const hasLeft = colIdx > 0 && row[colIdx - 1] === 1;
            const hasRight = colIdx < row.length - 1 && row[colIdx + 1] === 1;
            
            const radius = cellSize * 0.25;
            const borderRadius = `${!hasTop && !hasLeft ? radius : 0}px ${!hasTop && !hasRight ? radius : 0}px ${!hasBottom && !hasRight ? radius : 0}px ${!hasBottom && !hasLeft ? radius : 0}px`;
            
            return (
              <div
                key={colIdx}
                style={{
                  width: cellSize,
                  height: cellSize,
                  backgroundColor: '#DCECCB',
                  border: '1px solid rgba(139, 195, 74, 0.6)',
                  borderRadius,
                }}
              />
            );
          })}
        </div>
      ))}
    </div>
  );
}

// Lissajous animated background
function LissajousPuzzlePieces() {
  const [time, setTime] = useState(0);
  const containerRef = useRef<HTMLDivElement>(null);
  
  useEffect(() => {
    let animationId: number;
    let startTime = Date.now();
    
    const animate = () => {
      const elapsed = (Date.now() - startTime) / 1000;
      setTime(elapsed);
      animationId = requestAnimationFrame(animate);
    };
    
    animationId = requestAnimationFrame(animate);
    return () => cancelAnimationFrame(animationId);
  }, []);

  // Lissajous parameters
  const amplitudeX = 0.06;
  const amplitudeY = 0.08;
  const freqA = 3;
  const freqB = 2;
  const freqZ = 5;
  const period = 40; // seconds for full cycle

  return (
    <div ref={containerRef} className="absolute inset-0">
      {BASE_POSITIONS.slice(0, 12).map((pos, i) => {
        const shape = PIECE_SHAPES[i % PIECE_SHAPES.length];
        const phaseOffset = (i / 12) * Math.PI * 2;
        const speedFactor = 0.7 + (i % 4) * 0.15;
        
        const t = (time / period) * Math.PI * 2 * speedFactor + phaseOffset;
        
        // Lissajous curve
        const xOffset = amplitudeX * Math.sin(freqA * t + Math.PI / 4);
        const yOffset = amplitudeY * Math.sin(freqB * t);
        const zValue = Math.sin(freqZ * t + Math.PI / 3);
        
        // Depth effect
        const minScale = 0.6;
        const maxScale = 1.4;
        const scale = minScale + (maxScale - minScale) * ((zValue + 1) / 2);
        
        const minAlpha = 0.03;
        const maxAlpha = 0.12;
        const alpha = minAlpha + (maxAlpha - minAlpha) * ((zValue + 1) / 2);
        
        // Position
        const x = (pos[0] + xOffset) * 100;
        const y = (pos[1] + yOffset) * 100;
        
        // Rotation
        const rotation = (i * 30) + (time * 0.3 * 6 * (i % 2 === 0 ? 1 : -1));
        
        return (
          <div
            key={i}
            className="absolute"
            style={{
              left: `${x}%`,
              top: `${y}%`,
              transform: `rotate(${rotation}deg) scale(${scale})`,
              opacity: alpha,
              transition: 'none',
            }}
          >
            <PuzzlePiece shape={shape} cellSize={12} />
          </div>
        );
      })}
    </div>
  );
}

export default function Home() {
  return (
    <main className="min-h-screen bg-background">
      {/* Hero Section */}
      <section className="relative min-h-screen flex flex-col items-center justify-center px-4 overflow-hidden">
        {/* Background gradient */}
        <div className="absolute inset-0 bg-gradient-radial pointer-events-none" />

        <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.8 }}
          className="relative z-10 flex flex-col items-center text-center"
        >
          {/* Logo */}
          <motion.div
            initial={{ scale: 0.8, opacity: 0 }}
            animate={{ scale: 1, opacity: 1 }}
            transition={{ duration: 0.6, delay: 0.2 }}
            className="relative w-48 h-48 md:w-64 md:h-64 mb-8 rounded-full overflow-hidden ring-4 ring-primary/30 shadow-2xl shadow-primary/20"
          >
            <Image
              src="/images/goose.jpeg"
              alt="Gusak Lab Logo"
              fill
              className="object-cover"
              priority
            />
          </motion.div>

          {/* Title */}
          <motion.h1
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.6, delay: 0.4 }}
            className="text-5xl md:text-7xl font-bold mb-4"
          >
            <span className="text-gradient">Gusak Lab</span>
          </motion.h1>

          {/* Tagline */}
          <motion.p
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.6, delay: 0.5 }}
            className="text-xl md:text-2xl text-gray-400 mb-6"
          >
            Innovative Puzzle Games
          </motion.p>

          {/* Description */}
          <motion.p
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.6, delay: 0.6 }}
            className="max-w-xl text-gray-500 mb-8 leading-relaxed"
          >
            At Gusak Lab, we create innovative puzzle games that challenge your
            mind and provide hours of entertainment. Our games combine beautiful
            design with engaging gameplay to deliver unforgettable experiences.
          </motion.p>

          {/* Contact */}
          <motion.div
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.6, delay: 0.7 }}
            className="flex flex-col sm:flex-row items-center gap-3 sm:gap-6 text-primary mb-12"
          >
            <div className="flex items-center gap-2">
              <Mail className="w-5 h-5" />
              <a
                href="mailto:gusak.lab@gmail.com"
                className="hover:text-primary-dark transition-colors"
              >
                gusak.lab@gmail.com
              </a>
            </div>

            <span className="hidden sm:inline text-gray-600">|</span>

            <a
              href="mailto:contacts@gusaklab.com"
              className="hover:text-primary-dark transition-colors"
            >
              contacts@gusaklab.com
            </a>
          </motion.div>

          {/* Scroll indicator */}
          <motion.div
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            transition={{ duration: 0.6, delay: 1 }}
            className="absolute bottom-8"
          >
            <motion.div
              animate={{ y: [0, 10, 0] }}
              transition={{ duration: 1.5, repeat: Infinity }}
              className="w-6 h-10 border-2 border-gray-600 rounded-full flex justify-center pt-2"
            >
              <div className="w-1.5 h-1.5 bg-gray-600 rounded-full" />
            </motion.div>
          </motion.div>
        </motion.div>
      </section>

      {/* Features Section */}
      <section className="py-24 px-4">
        <div className="max-w-6xl mx-auto">
          <motion.h2
            initial={{ opacity: 0, y: 20 }}
            whileInView={{ opacity: 1, y: 0 }}
            viewport={{ once: true }}
            className="text-3xl md:text-4xl font-bold text-center mb-16"
          >
            Why Choose <span className="text-gradient">Our Games</span>
          </motion.h2>

          <div className="grid md:grid-cols-3 gap-8">
            {[
              {
                icon: Gamepad2,
                title: "Engaging Gameplay",
                description:
                  "Carefully crafted puzzles that keep you coming back for more.",
              },
              {
                icon: Sparkles,
                title: "Beautiful Design",
                description:
                  "Stunning visuals and smooth animations for an immersive experience.",
              },
              {
                icon: Users,
                title: "For Everyone",
                description:
                  "Games suitable for all ages, from casual players to puzzle enthusiasts.",
              },
            ].map((feature, index) => (
              <motion.div
                key={feature.title}
                initial={{ opacity: 0, y: 20 }}
                whileInView={{ opacity: 1, y: 0 }}
                viewport={{ once: true }}
                transition={{ delay: index * 0.1 }}
                className="bg-surface p-8 rounded-2xl border border-gray-800 hover:border-primary/50 transition-colors"
              >
                <feature.icon className="w-12 h-12 text-primary mb-4" />
                <h3 className="text-xl font-semibold mb-2">{feature.title}</h3>
                <p className="text-gray-500">{feature.description}</p>
              </motion.div>
            ))}
          </div>
        </div>
      </section>

      {/* Games Section */}
      <section className="py-24 px-4 bg-surface">
        <div className="max-w-6xl mx-auto">
          <motion.h2
            initial={{ opacity: 0, y: 20 }}
            whileInView={{ opacity: 1, y: 0 }}
            viewport={{ once: true }}
            className="text-3xl md:text-4xl font-bold text-center mb-16"
          >
            Our <span className="text-gradient">Games</span>
          </motion.h2>

          <motion.div
            initial={{ opacity: 0, y: 20 }}
            whileInView={{ opacity: 1, y: 0 }}
            viewport={{ once: true }}
            className="bg-surface-light p-8 md:p-12 rounded-3xl border border-gray-800"
          >
            <div className="flex flex-col md:flex-row items-center gap-8">
              <div className="w-32 h-32 bg-gradient-to-br from-primary to-purple-600 rounded-3xl flex items-center justify-center">
                <Gamepad2 className="w-16 h-16 text-white" />
              </div>
              <div className="flex-1 text-center md:text-left">
                <h3 className="text-2xl font-bold mb-2">PuzzleScroll</h3>
                <p className="text-gray-500 mb-6">
                  A unique puzzle experience that challenges your mind with
                  innovative mechanics and beautiful visuals.
                </p>
                <div className="flex flex-col sm:flex-row gap-4 justify-center md:justify-start">
                  <a
                    href={STORE_LINKS.appStore}
                    className="flex items-center justify-center gap-2 bg-white text-black px-6 py-3 rounded-xl font-medium hover:bg-gray-200 transition-colors"
                  >
                    <Apple className="w-5 h-5" />
                    App Store
                  </a>
                  <a
                    href={STORE_LINKS.googlePlay}
                    className="flex items-center justify-center gap-2 bg-primary text-white px-6 py-3 rounded-xl font-medium hover:bg-primary-dark transition-colors"
                  >
                    <Play className="w-5 h-5" />
                    Google Play
                  </a>
                </div>
              </div>
            </div>
          </motion.div>
        </div>
      </section>

      {/* Playable Demo Section */}
      <section className="py-24 px-4">
        <div className="max-w-6xl mx-auto text-center">
          <motion.h2
            initial={{ opacity: 0, y: 20 }}
            whileInView={{ opacity: 1, y: 0 }}
            viewport={{ once: true }}
            className="text-3xl md:text-4xl font-bold mb-4"
          >
            Try It <span className="text-gradient">Now</span>
          </motion.h2>
          <motion.p
            initial={{ opacity: 0, y: 20 }}
            whileInView={{ opacity: 1, y: 0 }}
            viewport={{ once: true }}
            transition={{ delay: 0.1 }}
            className="text-gray-500 mb-8"
          >
            Experience a quick demo right in your browser
          </motion.p>

          <motion.div
            initial={{ opacity: 0, scale: 0.95 }}
            whileInView={{ opacity: 1, scale: 1 }}
            viewport={{ once: true }}
            transition={{ delay: 0.2 }}
            className="bg-surface rounded-3xl border border-gray-800 overflow-hidden"
          >
            {/* Lissajous floating puzzle pieces - matching the game background */}
            <div className="aspect-video flex items-center justify-center relative overflow-hidden" style={{ backgroundColor: '#121212' }}>
              {/* Floating puzzle pieces with Lissajous movement */}
              <LissajousPuzzlePieces />

              {/* Center content */}
              <div className="relative z-10 text-center bg-black/60 backdrop-blur-sm px-12 py-8 rounded-2xl border border-[#8BC34A]/30">
                <motion.div
                  initial={{ scale: 0.8, opacity: 0 }}
                  whileInView={{ scale: 1, opacity: 1 }}
                  viewport={{ once: true }}
                  transition={{ delay: 0.3 }}
                >
                  <h3 className="text-2xl font-bold mb-2" style={{ color: '#8BC34A' }}>Elder Puzzle Scroll</h3>
                  <p className="text-gray-400 mb-6">Solve puzzles, challenge your mind</p>
                  <a
                    href="/playable/"
                    className="inline-flex items-center gap-2 text-black px-8 py-4 rounded-xl font-medium transition-all hover:scale-105 shadow-lg"
                    style={{ backgroundColor: '#8BC34A' }}
                  >
                    <Sparkles className="w-5 h-5" />
                    Play Now
                  </a>
                </motion.div>
              </div>
            </div>
          </motion.div>
        </div>
      </section>

      {/* Footer */}
      <footer className="py-12 px-4 border-t border-gray-800">
        <div className="max-w-6xl mx-auto text-center">
          <p className="text-gray-500">
            © 2026 Gusak Lab. All rights reserved.
          </p>
          <div className="mt-4">
            <a
              href="/en-US/privacy-policy/"
              className="text-gray-400 hover:text-primary transition-colors"
            >
              Privacy Policy
            </a>
          </div>
        </div>
      </footer>
    </main>
  );
}
