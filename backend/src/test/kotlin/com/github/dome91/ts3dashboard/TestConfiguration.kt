package com.github.dome91.ts3dashboard

import com.github.dome91.ts3dashboard.core.factories.IDGenerator
import com.github.dome91.ts3dashboard.core.repositories.TeamSpeakServerRepository

interface TestConfiguration {

    val teamSpeakServerRepository: TeamSpeakServerRepository

    val idGenerator: IDGenerator
}
