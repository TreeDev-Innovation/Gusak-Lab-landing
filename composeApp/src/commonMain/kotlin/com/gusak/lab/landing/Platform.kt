package com.gusak.lab.landing

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform