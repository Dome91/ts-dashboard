package com.github.dome91.tsdashboard.application.modules

import com.github.dome91.tsdashboard.application.repositories.InMemoryTeamSpeakServerRepository
import com.github.dome91.tsdashboard.core.repositories.TeamSpeakServerRepository
import org.koin.dsl.module


fun repositoryModule() = module {
    single<TeamSpeakServerRepository> { InMemoryTeamSpeakServerRepository() }
}
