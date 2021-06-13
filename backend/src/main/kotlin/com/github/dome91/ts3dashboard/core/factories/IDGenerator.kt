package com.github.dome91.ts3dashboard.core.factories

typealias ID = String

fun interface IDGenerator {
    fun generate(): ID
}
