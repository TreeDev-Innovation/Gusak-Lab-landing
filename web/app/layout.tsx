import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import GoogleAnalytics from "./components/GoogleAnalytics";

const inter = Inter({ subsets: ["latin", "cyrillic"] });

export const metadata: Metadata = {
  title: "Gusak Lab - Innovative Puzzle Games",
  description:
    "At Gusak Lab, we create innovative puzzle games that challenge your mind and provide hours of entertainment.",
  keywords: ["games", "puzzle", "mobile games", "iOS", "Android", "Gusak Lab"],
  authors: [{ name: "Gusak Lab" }],
  openGraph: {
    title: "Gusak Lab - Innovative Puzzle Games",
    description: "We create innovative puzzle games that challenge your mind.",
    type: "website",
    locale: "en_US",
  },
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body className={inter.className}>
        <GoogleAnalytics />
        {children}
      </body>
    </html>
  );
}
