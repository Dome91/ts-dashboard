package com.github.dome91.ts3dashboard.application.services

import com.github.dome91.ts3dashboard.application.ApplicationTestConfiguration
import com.github.dome91.ts3dashboard.application.testModule
import com.github.dome91.ts3dashboard.dsl.TeamSpeakServerBuilder.Companion.teamSpeakServer
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.ktor.application.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Test

internal class TeamSpeakServerApplicationServiceImplTest {

    @Test
    fun `validates TeamSpeak server with correct config`(): Unit = withTestApplication(Application::testModule) {
        ApplicationTestConfiguration {
            val testServer = teamSpeakServer {}
            val service = TeamSpeakServerApplicationServiceImpl()
            service.validate(testServer)
        }
    }

    @Test
    fun `queries channels with users in them`(): Unit = withTestApplication(Application::testModule) {
        ApplicationTestConfiguration {
            val testServer = teamSpeakServer {}
            val service = TeamSpeakServerApplicationServiceImpl()
            val channels = service.getChannels(testServer)
            channels.channels shouldHaveSize 1
            channels.channels[0].name shouldBe "Default Channel"
            channels.channels[0].users shouldHaveSize 0
        }
    }
}
