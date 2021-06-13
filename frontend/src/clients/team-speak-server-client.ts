import {TeamSpeakChannel, TeamSpeakServer} from "../model/team-speak-server";
import httpClient from "./http-client";

type GetServersResponse = {
    servers: TeamSpeakServer[]
}

type GetChannelsResponse = {
    channels: TeamSpeakChannel[]
}

class TeamSpeakServerClient {

    getServers(): Promise<TeamSpeakServer[]> {
        return httpClient.get<GetServersResponse>("/api/v1/teamspeakservers")
            .then(response => response.data.servers)
    }

    getChannels(id: string): Promise<TeamSpeakChannel[]> {
        return httpClient.get<GetChannelsResponse>(`/api/v1/teamspeakservers/${id}/channels`)
            .then(response => response.data.channels)
    }
}

const teamSpeakServerClient = new TeamSpeakServerClient()

export default teamSpeakServerClient
