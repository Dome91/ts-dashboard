package com.github.dome91.tsdashboard.application.modules

import com.github.dome91.tsdashboard.application.ApplicationStoppingEventListener
import com.github.dome91.tsdashboard.application.services.TeamSpeakServerApplicationServiceImpl
import com.github.dome91.tsdashboard.core.services.application.TeamSpeakServerApplicationService
import org.koin.dsl.binds
import org.koin.dsl.module

fun serviceModule() = module {
    single { TeamSpeakServerApplicationServiceImpl() }.binds(
        arrayOf(
            TeamSpeakServerApplicationService::class,
            ApplicationStoppingEventListener::class
        )
    )
}
