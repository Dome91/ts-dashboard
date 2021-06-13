package com.github.dome91.ts3dashboard.application.modules

import com.github.dome91.ts3dashboard.application.web.TeamSpeakServerDTOMapper
import com.github.dome91.ts3dashboard.application.web.TeamSpeakServerDTOMapperImpl
import org.koin.dsl.module

fun webModule() = module {
    single<TeamSpeakServerDTOMapper> { TeamSpeakServerDTOMapperImpl() }
}
