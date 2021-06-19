package com.github.dome91.ts3dashboard

import com.github.dome91.ts3dashboard.core.factories.IDGenerator
import com.github.dome91.ts3dashboard.core.repositories.TeamSpeakServerRepository
import com.github.dome91.ts3dashboard.core.services.application.TeamSpeakServerApplicationService

interface TestConfiguration {

    val teamSpeakServerRepository: TeamSpeakServerRepository
    val teamSpeakServerApplicationService: TeamSpeakServerApplicationService

    val idGenerator: IDGenerator
}
