package com.github.dome91.tsdashboard.core.factories

typealias ID = String

fun interface IDGenerator {
    fun generate(): ID
}
