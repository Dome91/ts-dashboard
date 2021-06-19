package com.github.dome91.ts3dashboard.application.services

import com.github.dome91.ts3dashboard.application.ApplicationStoppingEventListener
import com.github.dome91.ts3dashboard.core.exceptions.TeamSpeakServerNotFoundException
import com.github.dome91.ts3dashboard.core.factories.ID
import com.github.dome91.ts3dashboard.core.model.TeamSpeakChannel
import com.github.dome91.ts3dashboard.core.model.TeamSpeakChannels
import com.github.dome91.ts3dashboard.core.model.TeamSpeakServer
import com.github.dome91.ts3dashboard.core.model.TeamSpeakUser
import com.github.dome91.ts3dashboard.core.services.application.TeamSpeakServerApplicationService
import com.github.dome91.ts3dashboard.orThrow
import com.github.theholywaffle.teamspeak3.TS3Config
import com.github.theholywaffle.teamspeak3.TS3Query
import com.github.theholywaffle.teamspeak3.api.wrapper.Channel
import com.github.theholywaffle.teamspeak3.api.wrapper.Client
import org.slf4j.Logger
import org.slf4j.LoggerFactory


class TeamSpeakServerApplicationServiceImpl : TeamSpeakServerApplicationService, ApplicationStoppingEventListener {

    private val queries = HashMap<ID, TS3Query>()

    override fun add(teamSpeakServer: TeamSpeakServer) {
        val config = TS3Config().setHost(teamSpeakServer.address)
        val query = TS3Query(config)
        query.connect()
        query.api.login(teamSpeakServer.admin, teamSpeakServer.password)
        query.api.selectVirtualServerById(1)
        queries[teamSpeakServer.id] = query
        log.info("Added Teamspeak Server at address ${teamSpeakServer.address}")
    }

    override fun getChannels(teamSpeakServer: TeamSpeakServer): TeamSpeakChannels {
        val query = queries[teamSpeakServer.id] orThrow TeamSpeakServerNotFoundException.byID(teamSpeakServer.id)
        val clientsByChannel = query.api.clients.groupBy { it.channelId }
        val channels = query.api.channels

        val emptyChannels = getEmptyChannels(channels, clientsByChannel)
        val nonEmptyChannels = getNonEmptyChannels(channels, clientsByChannel)
        return TeamSpeakChannels(emptyChannels + nonEmptyChannels)
    }

    private fun getNonEmptyChannels(channels: List<Channel>, clientsByChannel: Map<Int, List<Client>>): List<TeamSpeakChannel> {
        return channels
            .filter { clientsByChannel.contains(it.id) }
            .map { TeamSpeakChannel(it.name, getTeamSpeakUsers(clientsByChannel.getValue(it.id))) }
    }

    private fun getEmptyChannels(channels: List<Channel>, clientsByChannel: Map<Int, List<Client>>): List<TeamSpeakChannel> {
        return channels
            .filter { !clientsByChannel.contains(it.id) }
            .map { TeamSpeakChannel(it.name, emptyList()) }
    }

    private fun getTeamSpeakUsers(clients: List<Client>): List<TeamSpeakUser> {
        return clients
            .filter { !it.nickname.startsWith(INVISIBLE_USER_PREFIX) }
            .map { TeamSpeakUser(it.nickname) }
    }

    override fun stop() {
        queries.values.forEach(TS3Query::exit)
        log.info("Closed all queries")
    }

    companion object {
        private const val INVISIBLE_USER_PREFIX = "serveradmin"
        val log: Logger = LoggerFactory.getLogger(TeamSpeakServerApplicationServiceImpl::class.java)
    }

}
