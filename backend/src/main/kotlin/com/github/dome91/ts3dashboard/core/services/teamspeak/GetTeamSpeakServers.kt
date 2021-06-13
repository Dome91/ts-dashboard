package com.github.dome91.ts3dashboard.core.services.teamspeak

import com.github.dome91.ts3dashboard.core.model.TeamSpeakServer
import com.github.dome91.ts3dashboard.core.model.TeamSpeakServers
import com.github.dome91.ts3dashboard.core.repositories.TeamSpeakServerRepository

class GetTeamSpeakServers(
    private val teamSpeakServerRepository: TeamSpeakServerRepository
) {
    operator fun invoke(): TeamSpeakServers {
        return TeamSpeakServers(teamSpeakServerRepository.findAll())
    }
}
