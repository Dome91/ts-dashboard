package com.github.dome91.tsdashboard

import com.github.dome91.tsdashboard.core.factories.IDGenerator
import com.github.dome91.tsdashboard.core.repositories.TeamSpeakServerRepository
import com.github.dome91.tsdashboard.core.services.application.TeamSpeakServerApplicationService

interface TestConfiguration {

    val teamSpeakServerRepository: TeamSpeakServerRepository
    val teamSpeakServerApplicationService: TeamSpeakServerApplicationService

    val idGenerator: IDGenerator
}
