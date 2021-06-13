import MockAdapter from "axios-mock-adapter";
import httpClient from "../../clients/http-client";
import {channel1, channel2, registerTeamSpeakServerMocks} from "../../clients/client-mocks";
import {render, screen, waitFor, within} from "@testing-library/react";
import Channels from "./channels";

describe("Channels Page", () => {
    const mock = new MockAdapter(httpClient)
    registerTeamSpeakServerMocks(mock)

    beforeEach(() => mock.resetHistory())

    it("should show channels with users", async () => {
        render(<Channels/>)

        await waitFor(() =>{
            screen.getByText(channel1.name)
            screen.getByText(channel2.name)
            channel1.users.forEach(user => screen.getByText(user.name))
            channel2.users.forEach(user => screen.getByText(user.name))
        })
    })
})
