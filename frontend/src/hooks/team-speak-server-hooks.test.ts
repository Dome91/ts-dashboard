import MockAdapter from "axios-mock-adapter";
import httpClient from "../clients/http-client";
import {channels, registerTeamSpeakServerMocks, server1, servers} from "../clients/client-mocks";
import {renderHook} from "@testing-library/react-hooks";
import {useChannels, useServers} from "./team-speak-server-hooks";

describe("TeamSpeak Server Hooks", () => {
    const mock = new MockAdapter(httpClient)
    registerTeamSpeakServerMocks(mock)

    beforeEach(() => mock.resetHistory())

    it('should return all servers', async () => {
        const {result, waitForNextUpdate} = renderHook(() => useServers())

        await waitForNextUpdate()

        expect(result.current).toStrictEqual(servers)
    });

    it('should return channels of server', async () => {
        const {result, waitForNextUpdate} = renderHook(() => useChannels(server1.id))

        await waitForNextUpdate()

        expect(result.current).toStrictEqual(channels)
    });
});
