package com.github.dome91.ts3dashboard.application.modules

import com.github.dome91.ts3dashboard.application.services.TeamSpeakServerApplicationServiceImpl
import com.github.dome91.ts3dashboard.core.services.application.TeamSpeakServerApplicationService
import org.koin.dsl.module

fun serviceModule() = module {
    single<TeamSpeakServerApplicationService> { TeamSpeakServerApplicationServiceImpl() }
}
