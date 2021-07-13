package com.github.dome91.tsdashboard.core.services.teamspeak

import com.github.dome91.tsdashboard.core.exceptions.TeamSpeakServerNotFoundException
import com.github.dome91.tsdashboard.core.factories.ID
import com.github.dome91.tsdashboard.core.model.TeamSpeakChannels
import com.github.dome91.tsdashboard.core.repositories.TeamSpeakServerRepository
import com.github.dome91.tsdashboard.core.services.application.TeamSpeakServerApplicationService
import com.github.dome91.tsdashboard.core.services.teamspeak.TeamSpeakChannelsSortedByOption.*
import com.github.dome91.tsdashboard.orThrow

enum class TeamSpeakChannelsSortedByOption {
    USERS_ASC,
    USERS_DESC
}

data class GetTeamSpeakChannelsCommand(val teamSpeakServerID: ID, val sortedByOption: TeamSpeakChannelsSortedByOption)

class GetTeamSpeakChannels(
    private val teamSpeakServerRepository: TeamSpeakServerRepository,
    private val teamSpeakServerApplicationService: TeamSpeakServerApplicationService
) {

    operator fun invoke(command: GetTeamSpeakChannelsCommand): TeamSpeakChannels {
        val id = command.teamSpeakServerID
        val server = teamSpeakServerRepository.findByID(id) orThrow TeamSpeakServerNotFoundException.byID(id)
        val channels = teamSpeakServerApplicationService.getChannels(server)
        return when (command.sortedByOption) {
            USERS_ASC -> sortByUsersAscending(channels)
            USERS_DESC -> sortByUsersDescending(channels)
        }
    }

    private fun sortByUsersAscending(channels: TeamSpeakChannels): TeamSpeakChannels =
        TeamSpeakChannels(channels.channels.sortedBy { it.users.size })

    private fun sortByUsersDescending(channels: TeamSpeakChannels): TeamSpeakChannels =
        TeamSpeakChannels(channels.channels.sortedByDescending { it.users.size })
}
