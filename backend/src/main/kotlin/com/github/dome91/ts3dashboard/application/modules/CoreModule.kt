package com.github.dome91.ts3dashboard.application.modules

import com.github.dome91.ts3dashboard.core.factories.IDGenerator
import com.github.dome91.ts3dashboard.core.factories.TeamSpeakServerFactory
import com.github.dome91.ts3dashboard.core.services.teamspeak.CreateTeamSpeakServer
import com.github.dome91.ts3dashboard.core.services.teamspeak.GetTeamSpeakChannels
import com.github.dome91.ts3dashboard.core.services.teamspeak.GetTeamSpeakServers
import org.koin.dsl.module
import java.util.*

fun coreModule() = module {
    single { IDGenerator { UUID.randomUUID().toString() } }
    single { TeamSpeakServerFactory(get()) }
    single { CreateTeamSpeakServer(get(), get(), get()) }
    single { GetTeamSpeakServers(get()) }
    single { GetTeamSpeakChannels(get(), get()) }
}
