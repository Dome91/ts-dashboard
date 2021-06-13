package com.github.dome91.ts3dashboard.core.services.teamspeak

import com.github.dome91.ts3dashboard.core.CoreTestConfiguration
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class CreateTeamSpeakServerTest {

    @Test
    fun `creates TeamSpeak server`() {
        CoreTestConfiguration {
            createTeamSpeakServer(CreateTeamSpeakServerCommand("address", "admin", "pass"))

            val servers = teamSpeakServerRepository.findAll()
            servers shouldHaveSize 1

            val server = servers[0]
            server.address shouldBe "address"
            server.admin shouldBe "admin"
            server.password shouldBe "pass"

            verify() { teamSpeakServerApplicationService.validate(any()) }
        }
    }

}
