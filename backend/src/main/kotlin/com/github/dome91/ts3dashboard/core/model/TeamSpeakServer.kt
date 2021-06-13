package com.github.dome91.ts3dashboard.core.model

import com.github.dome91.ts3dashboard.core.factories.ID

typealias TeamSpeakAdmin = String
typealias TeamSpeakAdminPassword = String
typealias Address = String

data class TeamSpeakServer(
    override val id: ID,
    val address: Address,
    val admin: TeamSpeakAdmin,
    val password: TeamSpeakAdminPassword
) :Entity
data class TeamSpeakServers(val servers: List<TeamSpeakServer>)

data class TeamSpeakUser(val name: String)
data class TeamSpeakChannel(val name: String, val users: List<TeamSpeakUser>)
data class TeamSpeakChannels(val channels: List<TeamSpeakChannel>)
