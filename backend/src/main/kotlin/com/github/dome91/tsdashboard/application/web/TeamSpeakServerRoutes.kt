package com.github.dome91.tsdashboard.application.web

import com.github.dome91.tsdashboard.core.services.teamspeak.GetTeamSpeakChannels
import com.github.dome91.tsdashboard.core.services.teamspeak.GetTeamSpeakChannelsCommand
import com.github.dome91.tsdashboard.core.services.teamspeak.GetTeamSpeakServers
import com.github.dome91.tsdashboard.core.services.teamspeak.TeamSpeakChannelsSortedByOption
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

    val resolveSortedByOption = { queryValue: String? ->
        when (queryValue) {
            "USERS_ASC" -> TeamSpeakChannelsSortedByOption.USERS_ASC
            "USERS_DESC" -> TeamSpeakChannelsSortedByOption.USERS_DESC
            else -> TeamSpeakChannelsSortedByOption.USERS_DESC
        }
    }

    get("/api/v1/teamspeakservers/{id}/channels") {
        call.parameters["id"]?.let {
            val sortedByOption = resolveSortedByOption(call.request.queryParameters["sortedBy"])
            val channels = getTeamSpeakChannels(GetTeamSpeakChannelsCommand(it, sortedByOption))
            val response = mapper.toResponse(channels)
            call.respond(response)
        }
    }
}
