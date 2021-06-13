import {TeamSpeakChannel, TeamSpeakServer} from "../model/team-speak-server";
import {useEffect, useState} from "react";
import teamSpeakServerClient from "../clients/team-speak-server-client";

export const useServers = (): TeamSpeakServer[] => {
    const [servers, setServers] = useState<TeamSpeakServer[]>([])
    useEffect(() => {
        teamSpeakServerClient.getServers().then(servers => setServers(servers))
    }, [])

    return servers
}

export const useChannels = (id: string): TeamSpeakChannel[] => {
    const [channels, setChannels] = useState<TeamSpeakChannel[]>([])
    useEffect(() => {
        teamSpeakServerClient.getChannels(id).then(channels => setChannels(channels))
    }, [id])

    return channels
}
