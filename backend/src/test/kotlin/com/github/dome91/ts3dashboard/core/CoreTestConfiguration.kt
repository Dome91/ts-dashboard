package com.github.dome91.ts3dashboard.core

import com.github.dome91.ts3dashboard.TestConfiguration
import com.github.dome91.ts3dashboard.application.repositories.InMemoryTeamSpeakServerRepository
import com.github.dome91.ts3dashboard.core.factories.IDGenerator
import com.github.dome91.ts3dashboard.core.factories.TeamSpeakServerFactory
import com.github.dome91.ts3dashboard.core.services.application.TeamSpeakServerApplicationService
import com.github.dome91.ts3dashboard.core.services.teamspeak.CreateTeamSpeakServer
import com.github.dome91.ts3dashboard.core.services.teamspeak.GetTeamSpeakChannels
import com.github.dome91.ts3dashboard.core.services.teamspeak.GetTeamSpeakServers
import io.mockk.mockk
import java.util.*

class CoreTestConfiguration(block: CoreTestConfiguration.() -> Unit) : TestConfiguration {

    // Repositories
    override val teamSpeakServerRepository = InMemoryTeamSpeakServerRepository()

    // Factories
    override val idGenerator: IDGenerator = IDGenerator { UUID.randomUUID().toString() }
    private val teamSpeakServerFactory = TeamSpeakServerFactory(idGenerator)

    // Application Services
    val teamSpeakServerApplicationService = mockk<TeamSpeakServerApplicationService>(relaxUnitFun = true)

    // Domain Services
    val createTeamSpeakServer = CreateTeamSpeakServer(teamSpeakServerFactory, teamSpeakServerApplicationService, teamSpeakServerRepository)
    val getTeamSpeakChannels = GetTeamSpeakChannels(teamSpeakServerRepository, teamSpeakServerApplicationService)
    val getTeamSpeakServers = GetTeamSpeakServers(teamSpeakServerRepository)

    init {
        block(this)
    }
}
