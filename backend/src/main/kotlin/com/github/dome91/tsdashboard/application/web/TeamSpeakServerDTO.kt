package com.github.dome91.tsdashboard.application.web

import com.github.dome91.tsdashboard.core.factories.ID
import kotlinx.serialization.Serializable

@Serializable
data class GetChannelsResponse(val channels: List<TeamSpeakChannelDTO>)

@Serializable
data class GetServersResponse(val servers: List<TeamSpeakServerDTO>)

@Serializable
data class TeamSpeakServerDTO(val id: ID)

@Serializable
data class TeamSpeakChannelDTO(val name: String, val users: List<TeamSpeakUserDTO>)

@Serializable
data class TeamSpeakUserDTO(val name: String)
