package com.github.dome91.tsdashboard.core.services.teamspeak

import com.github.dome91.tsdashboard.core.model.TeamSpeakServer
import com.github.dome91.tsdashboard.core.model.TeamSpeakServers
import com.github.dome91.tsdashboard.core.repositories.TeamSpeakServerRepository

class GetTeamSpeakServers(
    private val teamSpeakServerRepository: TeamSpeakServerRepository
) {
    operator fun invoke(): TeamSpeakServers {
        return TeamSpeakServers(teamSpeakServerRepository.findAll())
    }
}
