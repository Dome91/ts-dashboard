import MockAdapter from "axios-mock-adapter";
import httpClient from "./http-client";
import {channel1, channel2, registerTeamSpeakServerMocks, server1} from "./client-mocks";
import teamSpeakServerClient, {ChannelsSortedBy} from "./team-speak-server-client";

describe('TeamSpeak Server Client', () => {
    const mock = new MockAdapter(httpClient)
    registerTeamSpeakServerMocks(mock)

    beforeEach(() => mock.resetHistory())

    it('should return all servers', async () => {
        const servers = await teamSpeakServerClient.getServers()
        expect(servers.length).toBe(1)
        expect(servers[0].id).toBe(server1.id)
    })

    it('should return channels of server by number of users descending', async () => {
        const channels = await teamSpeakServerClient.getChannels(server1.id, ChannelsSortedBy.USERS_DESC)
        expect(channels.length).toBe(2)
        expect(channels[0].name).toBe(channel1.name)
        expect(channels[0].users).toStrictEqual(channel1.users)
        expect(channels[1].name).toBe(channel2.name)
        expect(channels[1].users).toStrictEqual(channel2.users)
    })

    it('should return channels of server by number of users ascending', async () => {
        const channels = await teamSpeakServerClient.getChannels(server1.id, ChannelsSortedBy.USERS_ASC)
        expect(channels.length).toBe(2)
        expect(channels[0].name).toBe(channel2.name)
        expect(channels[0].users).toStrictEqual(channel2.users)
        expect(channels[1].name).toBe(channel1.name)
        expect(channels[1].users).toStrictEqual(channel1.users)
    })
})
