package com.github.dome91.tsdashboard.application.modules

import com.github.dome91.tsdashboard.application.web.TeamSpeakServerDTOMapper
import com.github.dome91.tsdashboard.application.web.TeamSpeakServerDTOMapperImpl
import org.koin.dsl.module

fun webModule() = module {
    single<TeamSpeakServerDTOMapper> { TeamSpeakServerDTOMapperImpl() }
}
