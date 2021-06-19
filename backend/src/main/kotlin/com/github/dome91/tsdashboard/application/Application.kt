package com.github.dome91.tsdashboard.application

import com.github.dome91.tsdashboard.application.initialization.CreateTeamSpeakServerInitializer
import com.github.dome91.tsdashboard.application.modules.*
import com.github.dome91.tsdashboard.application.web.teamSpeakServerRoutes
import com.github.lamba92.ktor.features.SinglePageApplication
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.netty.*
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.KoinApplicationStopPreparing
import org.koin.ktor.ext.inject
import org.slf4j.event.Level

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.main() {
    registerPlugins()
    registerRoutes()
    registerEventListener()
    initialize()
}

fun Application.registerPlugins() {
    install(Routing)
    install(DefaultHeaders)
    install(CallLogging) {
        level = Level.INFO
    }
    install(ContentNegotiation) {
        json()
    }
    install(CORS) {
        anyHost()
    }
    install(Koin) {
        modules(coreModule(), repositoryModule(), serviceModule(), webModule(), initializationModule())
    }

    install(SinglePageApplication) {
        folderPath = "public"
    }

}

fun Application.registerRoutes() {
    teamSpeakServerRoutes()
}

fun Application.initialize() {
    val createTeamSpeakServerInitializer: CreateTeamSpeakServerInitializer by inject()
    createTeamSpeakServerInitializer.initialize()
}

fun Application.registerEventListener() {
    environment.monitor.subscribe(KoinApplicationStopPreparing) {
        val listener: ApplicationStoppingEventListener by inject()
        listener.stop()
    }
}

interface ApplicationStoppingEventListener {
    fun stop()
}
