package com.github.dome91.tsdashboard.application.web

import com.github.dome91.tsdashboard.application.common.MapperConfiguration
import com.github.dome91.tsdashboard.core.model.TeamSpeakChannels
import com.github.dome91.tsdashboard.core.model.TeamSpeakServer
import com.github.dome91.tsdashboard.core.model.TeamSpeakServers
import com.github.dome91.tsdashboard.core.model.TeamSpeakUser
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(config = MapperConfiguration::class)
interface TeamSpeakServerDTOMapper {

    @Mapping(target = "copy", ignore = true)
    fun toDTO(user: TeamSpeakUser): TeamSpeakUserDTO

    @Mapping(target = "copy", ignore = true)
    fun toDTO(server: TeamSpeakServer): TeamSpeakServerDTO

    @Mapping(target = "copy", ignore = true)
    fun toResponse(teamSpeakChannels: TeamSpeakChannels): GetChannelsResponse

    @Mapping(target = "copy", ignore = true)
    fun toResponse(teamSpeakServers: TeamSpeakServers): GetServersResponse


}
