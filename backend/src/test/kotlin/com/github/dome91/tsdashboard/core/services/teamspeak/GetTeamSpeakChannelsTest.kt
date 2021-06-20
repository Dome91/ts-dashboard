package com.github.dome91.tsdashboard.core.services.teamspeak

import com.github.dome91.tsdashboard.core.CoreTestConfiguration
import com.github.dome91.tsdashboard.dsl.TeamSpeakChannelsBuilder.Companion.teamSpeakChannels
import com.github.dome91.tsdashboard.dsl.TeamSpeakServerBuilder.Companion.teamSpeakServer
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldHaveSize
import io.mockk.every
import org.junit.jupiter.api.Test

internal class GetTeamSpeakChannelsTest {

    @Test
    fun `gets channels of TeamSpeak server`() {
        CoreTestConfiguration {
            val server = teamSpeakServer {}

            val channels = teamSpeakChannels {
                teamSpeakChannel { name = "channel1"; teamSpeakUser { name = "user1" } }
                teamSpeakChannel { name = "channel2"; teamSpeakUser { name = "user2" } }
            }
            every { teamSpeakServerApplicationService.getChannels(server) } returns channels

            val returnedChannels = getTeamSpeakChannels(GetTeamSpeakChannelsCommand(server.id))
            returnedChannels.channels shouldHaveSize 2
            returnedChannels.channels shouldContainAll channels.channels
        }
    }

}
