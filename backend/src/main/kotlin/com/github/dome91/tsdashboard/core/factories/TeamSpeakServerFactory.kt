package com.github.dome91.tsdashboard.core.factories

import com.github.dome91.tsdashboard.core.model.*

class TeamSpeakServerFactory(private val idGenerator: IDGenerator) {

    fun build(address: Address, teamSpeakAdmin: TeamSpeakAdmin, teamSpeakAdminPassword: TeamSpeakAdminPassword) =
        TeamSpeakServer(idGenerator.generate(), address, teamSpeakAdmin, teamSpeakAdminPassword)

}
