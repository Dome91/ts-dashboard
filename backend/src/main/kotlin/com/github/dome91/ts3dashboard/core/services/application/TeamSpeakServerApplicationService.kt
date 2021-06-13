package com.github.dome91.ts3dashboard.core.services.application

import com.github.dome91.ts3dashboard.core.model.TeamSpeakChannels
import com.github.dome91.ts3dashboard.core.model.TeamSpeakServer

interface TeamSpeakServerApplicationService {

    fun validate(teamSpeakServer: TeamSpeakServer)
    fun getChannels(teamSpeakServer: TeamSpeakServer): TeamSpeakChannels

}
