package com.github.dome91.tsdashboard


infix fun <T, E : RuntimeException> T?.orThrow(e: E): T {
    if (this != null) {
        return this
    }
    throw e
}
