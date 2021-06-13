package com.github.dome91.ts3dashboard.application.modules

import com.github.dome91.ts3dashboard.application.repositories.InMemoryTeamSpeakServerRepository
import com.github.dome91.ts3dashboard.core.repositories.TeamSpeakServerRepository
import org.koin.dsl.module


fun repositoryModule() = module {
    single<TeamSpeakServerRepository> { InMemoryTeamSpeakServerRepository() }
}
