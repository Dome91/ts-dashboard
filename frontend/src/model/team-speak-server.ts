export type TeamSpeakServer = {
    id: string
}

export type TeamSpeakChannel = {
    name: string,
    users: TeamSpeakUser[]
}

export type TeamSpeakUser = {
    name: string
}
