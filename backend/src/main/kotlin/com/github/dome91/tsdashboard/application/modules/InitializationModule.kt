package com.github.dome91.tsdashboard.application.modules

import com.github.dome91.tsdashboard.application.initialization.CreateTeamSpeakServerInitializer
import io.ktor.application.*
import org.koin.dsl.module
import org.koin.ktor.ext.get

fun Application.initializationModule() = module {
    single { buildCreateTeamSpeakServerInitializer() }
}

fun Application.buildCreateTeamSpeakServerInitializer(): CreateTeamSpeakServerInitializer {
    val address = environment.config.property("teamspeakserver.address").getString()
    val admin = environment.config.property("teamspeakserver.admin").getString()
    val password = environment.config.property("teamspeakserver.password").getString()
    return CreateTeamSpeakServerInitializer(address, admin, password, get())
}
