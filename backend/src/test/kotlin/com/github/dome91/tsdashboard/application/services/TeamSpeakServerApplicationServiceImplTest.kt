package com.github.dome91.tsdashboard.application.services

import com.github.dome91.tsdashboard.application.ApplicationTestConfiguration
import com.github.dome91.tsdashboard.application.testModule
import com.github.dome91.tsdashboard.dsl.TeamSpeakServerBuilder.Companion.teamSpeakServer
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.ktor.application.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Test

internal class TeamSpeakServerApplicationServiceImplTest {

    @Test
    fun `add TeamSpeak server with correct config`(): Unit = withTestApplication(Application::testModule) {
        ApplicationTestConfiguration {
            val testServer = teamSpeakServer {}
            teamSpeakServerApplicationService.add(testServer)
        }
    }

    @Test
    fun `queries channels with users in them`(): Unit = withTestApplication(Application::testModule) {
        ApplicationTestConfiguration {
            val testServer = teamSpeakServer {}
            val channels = teamSpeakServerApplicationService.getChannels(testServer)
            channels.channels shouldHaveSize 1
            channels.channels[0].name shouldBe "Default Channel"
            channels.channels[0].users shouldHaveSize 0
        }
    }
}
