package com.github.dome91.ts3dashboard.application.web

import com.github.dome91.ts3dashboard.application.ApplicationTestConfiguration
import com.github.dome91.ts3dashboard.application.testModule
import com.github.dome91.ts3dashboard.core.factories.ID
import com.github.dome91.ts3dashboard.dsl.TeamSpeakServerBuilder.Companion.teamSpeakServer
import com.jayway.jsonpath.JsonPath.read
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Test

internal class TeamSpeakServerRoutesKtTest {

    @Test
    fun `get servers`(): Unit = withTestApplication(Application::testModule) {
        ApplicationTestConfiguration {
            val server1 = teamSpeakServer { }
            val server2 = teamSpeakServer { }

            with(handleRequest(HttpMethod.Get, "/api/v1/teamspeakservers")) {
                response.status() shouldBe HttpStatusCode.OK

                val body = response.content
                read<List<TeamSpeakServerDTO>>(body, "$.servers") shouldHaveSize 2
                read<ID>(body, "$.servers[0].id") shouldBeIn listOf(server1.id, server2.id)
                read<ID>(body, "$.servers[1].id") shouldBeIn listOf(server1.id, server2.id)
            }
        }
    }

    @Test
    fun `get channels of TeamSpeakServer`(): Unit = withTestApplication(Application::testModule) {
        ApplicationTestConfiguration {
            val server = teamSpeakServer { }

            with(handleRequest(HttpMethod.Get, "/api/v1/teamspeakservers/${server.id}/channels")) {
                response.status() shouldBe HttpStatusCode.OK

                val body = response.content
                read<List<TeamSpeakChannelDTO>>(body, "$.channels") shouldHaveSize 1
                read<String>(body, "$.channels[0].name") shouldBe "Default Channel"
                read<List<TeamSpeakUserDTO>>(body, "$.channels[0].users") shouldHaveSize 0
            }
        }
    }
}
