package com.github.dome91.tsdashboard.core.services.teamspeak

import com.github.dome91.tsdashboard.core.factories.TeamSpeakServerFactory
import com.github.dome91.tsdashboard.core.model.Address
import com.github.dome91.tsdashboard.core.model.TeamSpeakAdmin
import com.github.dome91.tsdashboard.core.model.TeamSpeakAdminPassword
import com.github.dome91.tsdashboard.core.repositories.TeamSpeakServerRepository
import com.github.dome91.tsdashboard.core.services.application.TeamSpeakServerApplicationService

data class CreateTeamSpeakServerCommand(
    val address: Address,
    val teamSpeakAdmin: TeamSpeakAdmin,
    val teamSpeakAdminPassword: TeamSpeakAdminPassword
)

class CreateTeamSpeakServer(
    private val teamSpeakServerFactory: TeamSpeakServerFactory,
    private val teamSpeakServerApplicationService: TeamSpeakServerApplicationService,
    private val teamSpeakServerRepository: TeamSpeakServerRepository
) {

    operator fun invoke(command: CreateTeamSpeakServerCommand) {
        val teamSpeakServer = teamSpeakServerFactory.build(command.address, command.teamSpeakAdmin, command.teamSpeakAdminPassword)
        teamSpeakServerApplicationService.add(teamSpeakServer)
        teamSpeakServerRepository.save(teamSpeakServer)
    }

}
