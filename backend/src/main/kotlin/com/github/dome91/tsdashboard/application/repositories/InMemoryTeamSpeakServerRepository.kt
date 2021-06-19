package com.github.dome91.tsdashboard.application.repositories

import com.github.dome91.tsdashboard.core.model.TeamSpeakServer
import com.github.dome91.tsdashboard.core.repositories.TeamSpeakServerRepository

class InMemoryTeamSpeakServerRepository : TeamSpeakServerRepository, InMemoryRepository<TeamSpeakServer>() {
}
