package com.github.dome91.tsdashboard.core.services.application

import com.github.dome91.tsdashboard.core.model.TeamSpeakChannels
import com.github.dome91.tsdashboard.core.model.TeamSpeakServer

interface TeamSpeakServerApplicationService {

    fun add(teamSpeakServer: TeamSpeakServer)
    fun getChannels(teamSpeakServer: TeamSpeakServer): TeamSpeakChannels

}
