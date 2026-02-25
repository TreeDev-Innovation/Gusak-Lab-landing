"use client";

import { motion } from "framer-motion";
import Image from "next/image";
import { Mail, Apple, Play, Gamepad2, Sparkles, Users } from "lucide-react";

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
          <motion.a
            href="mailto:gusak.lab@gmail.com"
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.6, delay: 0.7 }}
            className="flex items-center gap-2 text-primary hover:text-primary-dark transition-colors mb-12"
          >
            <Mail className="w-5 h-5" />
            <span>gusak.lab@gmail.com</span>
          </motion.a>

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
                    href="#"
                    className="flex items-center justify-center gap-2 bg-white text-black px-6 py-3 rounded-xl font-medium hover:bg-gray-200 transition-colors"
                  >
                    <Apple className="w-5 h-5" />
                    App Store
                  </a>
                  <a
                    href="#"
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
            {/* Placeholder for Compose WASM playable */}
            <div className="aspect-video flex items-center justify-center bg-surface-light">
              <div className="text-center">
                <Gamepad2 className="w-16 h-16 text-gray-600 mx-auto mb-4" />
                <p className="text-gray-500 mb-4">Playable Demo</p>
                <a
                  href="/playable/"
                  className="inline-flex items-center gap-2 bg-primary text-white px-6 py-3 rounded-xl font-medium hover:bg-primary-dark transition-colors"
                >
                  <Sparkles className="w-5 h-5" />
                  Launch Demo
                </a>
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
        </div>
      </footer>
    </main>
  );
}
