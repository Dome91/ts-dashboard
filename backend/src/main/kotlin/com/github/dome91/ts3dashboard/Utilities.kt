package com.github.dome91.ts3dashboard


infix fun <T, E : RuntimeException> T?.orThrow(e: E): T {
    if (this != null) {
        return this
    }
    throw e
}
