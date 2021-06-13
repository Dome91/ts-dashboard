package com.github.dome91.ts3dashboard.core.services.teamspeak

import com.github.dome91.ts3dashboard.core.CoreTestConfiguration
import com.github.dome91.ts3dashboard.dsl.TeamSpeakServerBuilder.Companion.teamSpeakServer
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import org.junit.jupiter.api.Test

internal class GetTeamSpeakServersTest {

    @Test
    fun `return all team speak servers`() {
        CoreTestConfiguration {
            val server1 = teamSpeakServer {  }
            val server2 = teamSpeakServer {  }

            val servers = getTeamSpeakServers()
            servers.servers shouldContainExactlyInAnyOrder listOf(server1, server2)
        }
    }
}
