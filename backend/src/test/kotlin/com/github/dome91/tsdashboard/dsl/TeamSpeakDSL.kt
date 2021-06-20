package com.github.dome91.tsdashboard.dsl

import com.github.dome91.tsdashboard.TestConfiguration
import com.github.dome91.tsdashboard.core.factories.ID
import com.github.dome91.tsdashboard.core.model.*

class TeamSpeakUserBuilder private constructor(var name: String = "teamSpeakUser") {
    fun build() = TeamSpeakUser(name)

    companion object {
        fun teamSpeakUser(block: TeamSpeakUserBuilder.() -> Unit): TeamSpeakUser {
            val builder = TeamSpeakUserBuilder()
            block(builder)
            return builder.build()
        }
    }
}

class TeamSpeakChannelBuilder private constructor(
    var name: String = "teamSpeakChannel",
    private var users: MutableList<TeamSpeakUser> = mutableListOf()
) {
    fun build() = TeamSpeakChannel(name, users)
    fun teamSpeakUser(block: TeamSpeakUserBuilder.() -> Unit) {
        users.add(TeamSpeakUserBuilder.teamSpeakUser { block() })
    }

    companion object {
        fun teamSpeakChannel(block: TeamSpeakChannelBuilder.() -> Unit): TeamSpeakChannel {
            val builder = TeamSpeakChannelBuilder()
            block(builder)
            return builder.build()
        }
    }
}

class TeamSpeakChannelsBuilder private constructor(private var channels: MutableList<TeamSpeakChannel> = mutableListOf()) {
    fun build() = TeamSpeakChannels(channels)
    fun teamSpeakChannel(block: TeamSpeakChannelBuilder.() -> Unit) {
        channels.add(TeamSpeakChannelBuilder.teamSpeakChannel { block() })
    }

    companion object {
        fun teamSpeakChannels(block: TeamSpeakChannelsBuilder.() -> Unit): TeamSpeakChannels {
            val builder = TeamSpeakChannelsBuilder()
            block(builder)
            return builder.build()
        }
    }
}

class TeamSpeakServerBuilder private constructor(
    var id: ID,
    var address: Address = "localhost",
    var admin: TeamSpeakAdmin = "serveradmin",
    var password: TeamSpeakAdminPassword = "test"
) {
    fun build() = TeamSpeakServer(id, address, admin, password)

    companion object {
        fun TestConfiguration.teamSpeakServer(block: TeamSpeakServerBuilder.() -> Unit): TeamSpeakServer {
            val builder = TeamSpeakServerBuilder(idGenerator.generate())
            block(builder)
            return builder.build().also {
                teamSpeakServerApplicationService.add(it)
                teamSpeakServerRepository.save(it)
            }
        }
    }
}
