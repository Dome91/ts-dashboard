package com.github.dome91.ts3dashboard.core.factories

import com.github.dome91.ts3dashboard.core.model.*

class TeamSpeakServerFactory(private val idGenerator: IDGenerator) {

    fun build(address: Address, teamSpeakAdmin: TeamSpeakAdmin, teamSpeakAdminPassword: TeamSpeakAdminPassword) =
        TeamSpeakServer(idGenerator.generate(), address, teamSpeakAdmin, teamSpeakAdminPassword)

}
