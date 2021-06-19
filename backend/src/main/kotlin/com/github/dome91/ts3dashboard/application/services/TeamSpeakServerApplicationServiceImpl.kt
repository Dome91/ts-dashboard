package com.github.dome91.ts3dashboard.application.services

import com.github.dome91.ts3dashboard.application.ApplicationStoppingEventListener
import com.github.dome91.ts3dashboard.core.exceptions.TeamSpeakServerNotFoundException
import com.github.dome91.ts3dashboard.core.factories.ID
import com.github.dome91.ts3dashboard.core.model.TeamSpeakChannel
import com.github.dome91.ts3dashboard.core.model.TeamSpeakChannels
import com.github.dome91.ts3dashboard.core.model.TeamSpeakServer
import com.github.dome91.ts3dashboard.core.services.application.TeamSpeakServerApplicationService
import com.github.dome91.ts3dashboard.orThrow
import com.github.theholywaffle.teamspeak3.TS3Config
import com.github.theholywaffle.teamspeak3.TS3Query
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
        query.api.clients.map { it.nickname }
        val channels = query.api.channels.map { TeamSpeakChannel(it.name, listOf()) }
        return TeamSpeakChannels(channels)
    }

    override fun stop() {
        queries.values.forEach(TS3Query::exit)
        log.info("Closed all queries")
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(TeamSpeakServerApplicationServiceImpl::class.java)
    }

}
