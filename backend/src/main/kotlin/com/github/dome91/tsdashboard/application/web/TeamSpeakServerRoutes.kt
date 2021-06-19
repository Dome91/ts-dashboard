package com.github.dome91.tsdashboard.application.web

import com.github.dome91.tsdashboard.core.services.teamspeak.GetTeamSpeakChannels
import com.github.dome91.tsdashboard.core.services.teamspeak.GetTeamSpeakChannelsCommand
import com.github.dome91.tsdashboard.core.services.teamspeak.GetTeamSpeakServers
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Application.teamSpeakServerRoutes() {
    routing {
        getServers()
        getChannels()
    }
}

fun Route.getServers() {
    val getTeamSpeakServers: GetTeamSpeakServers by inject()
    val mapper: TeamSpeakServerDTOMapper by inject()

    get("/api/v1/teamspeakservers") {
        val servers = getTeamSpeakServers()
        val response = mapper.toResponse(servers)
        call.respond(response)
    }
}

fun Route.getChannels() {
    val getTeamSpeakChannels: GetTeamSpeakChannels by inject()
    val mapper: TeamSpeakServerDTOMapper by inject()

    get("/api/v1/teamspeakservers/{id}/channels") {
        call.parameters["id"]?.let {
            val channels = getTeamSpeakChannels(GetTeamSpeakChannelsCommand(it))
            val response = mapper.toResponse(channels)
            call.respond(response)
        }
    }
}
