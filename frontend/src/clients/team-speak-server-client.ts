import {TeamSpeakChannel, TeamSpeakServer} from "../model/team-speak-server";
import httpClient from "./http-client";

type GetServersResponse = {
    servers: TeamSpeakServer[]
}

type GetChannelsResponse = {
    channels: TeamSpeakChannel[]
}

export enum ChannelsSortedBy {
    USERS_ASC = "USERS_ASC",
    USERS_DESC = "USERS_DESC"
}

class TeamSpeakServerClient {

    getServers(): Promise<TeamSpeakServer[]> {
        return httpClient.get<GetServersResponse>("/api/v1/teamspeakservers")
            .then(response => response.data.servers)
    }

    getChannels(id: string, sortedBy: ChannelsSortedBy): Promise<TeamSpeakChannel[]> {
        return httpClient.get<GetChannelsResponse>(`/api/v1/teamspeakservers/${id}/channels?sortedBy=${sortedBy}`)
            .then(response => response.data.channels)
    }
}

const teamSpeakServerClient = new TeamSpeakServerClient()

export default teamSpeakServerClient
