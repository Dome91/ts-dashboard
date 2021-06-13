package com.github.dome91.ts3dashboard.application.services

import com.github.dome91.ts3dashboard.core.model.TeamSpeakChannel
import com.github.dome91.ts3dashboard.core.model.TeamSpeakChannels
import com.github.dome91.ts3dashboard.core.model.TeamSpeakServer
import com.github.dome91.ts3dashboard.core.services.application.TeamSpeakServerApplicationService
import com.github.theholywaffle.teamspeak3.TS3Config
import com.github.theholywaffle.teamspeak3.TS3Query


class TeamSpeakServerApplicationServiceImpl : TeamSpeakServerApplicationService {

    override fun validate(teamSpeakServer: TeamSpeakServer) {
        login(teamSpeakServer) {}
    }

    override fun getChannels(teamSpeakServer: TeamSpeakServer): TeamSpeakChannels {
        return login(teamSpeakServer) {
            api.clients.map { it.nickname }
            val channels = api.channels.map { TeamSpeakChannel(it.name, listOf()) }
            TeamSpeakChannels(channels)
        }
    }

    private fun <R> login(teamSpeakServer: TeamSpeakServer, block: TS3Query.() -> R): R {
        val config = TS3Config().setHost(teamSpeakServer.address)
        val query = TS3Query(config)
        query.connect()

        val result: R
        try {
            query.api.login(teamSpeakServer.admin, teamSpeakServer.password)
            query.api.selectVirtualServerById(1)
            result = block(query)
        } finally {
            query.exit()
        }

        return result
    }
}
