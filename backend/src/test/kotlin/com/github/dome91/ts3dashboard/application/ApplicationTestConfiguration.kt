package com.github.dome91.tsdashboard.application

import com.github.dome91.tsdashboard.TestConfiguration
import com.github.dome91.tsdashboard.application.services.TeamSpeakServerApplicationServiceImpl
import com.github.dome91.tsdashboard.core.factories.IDGenerator
import com.github.dome91.tsdashboard.core.repositories.TeamSpeakServerRepository
import com.github.dome91.tsdashboard.core.services.application.TeamSpeakServerApplicationService
import io.ktor.application.*
import io.ktor.config.*
import org.koin.java.KoinJavaComponent

class ApplicationTestConfiguration(block: ApplicationTestConfiguration.() -> Unit) : TestConfiguration {

    override val idGenerator: IDGenerator by KoinJavaComponent.inject(IDGenerator::class.java)
    override val teamSpeakServerRepository: TeamSpeakServerRepository by KoinJavaComponent.inject(TeamSpeakServerRepository::class.java)
    override val teamSpeakServerApplicationService: TeamSpeakServerApplicationService by KoinJavaComponent.inject(
        TeamSpeakServerApplicationServiceImpl::class.java
    )

    init {
        teamSpeakServerRepository.deleteAll()
        block(this)
    }
}

fun Application.testModule() = run {
    (environment.config as MapApplicationConfig).apply {
        put("teamspeakserver.address", System.getenv("TS_ADDRESS") ?: "localhost")
        put("teamspeakserver.admin", "serveradmin")
        put("teamspeakserver.password", "test")
        main()
    }
}
