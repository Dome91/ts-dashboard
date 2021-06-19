package com.github.dome91.tsdashboard.application.initialization

import com.github.dome91.tsdashboard.core.model.Address
import com.github.dome91.tsdashboard.core.model.TeamSpeakAdmin
import com.github.dome91.tsdashboard.core.model.TeamSpeakAdminPassword
import com.github.dome91.tsdashboard.core.services.teamspeak.CreateTeamSpeakServer
import com.github.dome91.tsdashboard.core.services.teamspeak.CreateTeamSpeakServerCommand
import org.slf4j.LoggerFactory

class CreateTeamSpeakServerInitializer(
    private val address: Address,
    private val teamSpeakAdmin: TeamSpeakAdmin,
    private val teamSpeakAdminPassword: TeamSpeakAdminPassword,
    private val createTeamSpeakServer: CreateTeamSpeakServer
) {
    fun initialize() {
        createTeamSpeakServer(CreateTeamSpeakServerCommand(address, teamSpeakAdmin, teamSpeakAdminPassword))
        log.info("Created TeamSpeakServer connection at address: $address")
    }

    companion object {
        private val log = LoggerFactory.getLogger(CreateTeamSpeakServerInitializer::class.java)
    }
}
