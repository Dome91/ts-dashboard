package com.github.dome91.tsdashboard.core.services.teamspeak

import com.github.dome91.tsdashboard.core.exceptions.TeamSpeakServerNotFoundException
import com.github.dome91.tsdashboard.core.factories.ID
import com.github.dome91.tsdashboard.core.model.TeamSpeakChannels
import com.github.dome91.tsdashboard.core.repositories.TeamSpeakServerRepository
import com.github.dome91.tsdashboard.core.services.application.TeamSpeakServerApplicationService
import com.github.dome91.tsdashboard.orThrow

data class GetTeamSpeakChannelsCommand(val teamSpeakServerID: ID)

class GetTeamSpeakChannels(
    private val teamSpeakServerRepository: TeamSpeakServerRepository,
    private val teamSpeakServerApplicationService: TeamSpeakServerApplicationService
) {

    operator fun invoke(command: GetTeamSpeakChannelsCommand): TeamSpeakChannels {
        val id = command.teamSpeakServerID
        val server = teamSpeakServerRepository.findByID(id) orThrow TeamSpeakServerNotFoundException.byID(id)
        return teamSpeakServerApplicationService.getChannels(server)
    }

}
