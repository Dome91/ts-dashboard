package com.github.dome91.ts3dashboard.application.repositories

import com.github.dome91.ts3dashboard.core.model.TeamSpeakServer
import com.github.dome91.ts3dashboard.core.repositories.TeamSpeakServerRepository

class InMemoryTeamSpeakServerRepository : TeamSpeakServerRepository, InMemoryRepository<TeamSpeakServer>() {
}
