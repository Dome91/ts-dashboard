import MockAdapter from "axios-mock-adapter";
import {TeamSpeakChannel, TeamSpeakServer, TeamSpeakUser} from "../model/team-speak-server";

export const user1: TeamSpeakUser = {name: "user1"}
export const user2: TeamSpeakUser = {name: "user2"}
export const user3: TeamSpeakUser = {name: "user3"}
export const channel1: TeamSpeakChannel = {name: "channelName1", users: [user1, user2]}
export const channel2: TeamSpeakChannel = {name: "channelName2", users: [user3]}
export const channels: TeamSpeakChannel[] = [channel1, channel2]
export const server1: TeamSpeakServer = {id: "serverId1"}
export const servers: TeamSpeakServer[] = [server1]

export const registerTeamSpeakServerMocks = (mock: MockAdapter) => {
    mock.onGet("/api/v1/teamspeakservers").reply(200, {servers})
    mock.onGet(`/api/v1/teamspeakservers/${server1.id}/channels?sortedBy=USERS_ASC`).reply(200, {channels: [channel2, channel1]})
    mock.onGet(`/api/v1/teamspeakservers/${server1.id}/channels?sortedBy=USERS_DESC`).reply(200, {channels: [channel1, channel2]})
}
