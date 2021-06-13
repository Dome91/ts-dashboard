import {useChannels, useServers} from "../../hooks/team-speak-server-hooks";
import {Card, Col, Container, ListGroup, Row} from "react-bootstrap";
import {TeamSpeakChannel, TeamSpeakUser} from "../../model/team-speak-server";

type ChannelCardUserEntryProps = {
    user: TeamSpeakUser
}

const ChannelCardUserEntry = ({user}: ChannelCardUserEntryProps): JSX.Element => {
    return <ListGroup.Item>{user.name}</ListGroup.Item>
}

type ChannelCardProps = {
    channel: TeamSpeakChannel,
}

const ChannelCard = ({channel}: ChannelCardProps): JSX.Element => {
    const userEntries = channel.users.map((user, index) => <ChannelCardUserEntry user={user} key={index}/>)
    return <Card>
        <Card.Header>
            {channel.name}
        </Card.Header>
        <ListGroup variant="flush">
            {userEntries.length > 0 ? userEntries : <div>Channel is empty.</div>}
        </ListGroup>
    </Card>
}

type ChannelCardsProps = {
    serverId: string
}

const ChannelCards = ({serverId}: ChannelCardsProps): JSX.Element => {
    const channels = useChannels(serverId)
    const channelCards = channels.map((channel, index) => <ChannelCard channel={channel} key={index}/>)
    return <>
        {channelCards}
    </>
}

const Channels = () => {
    const servers = useServers()
    return <Container>
        <Row>
            <Col lg={2} md={3} sm={4} xs={6}>
                {servers.length > 0 ? <ChannelCards serverId={servers[0].id}/> : <div/>}
            </Col>
        </Row>
    </Container>
}

export default Channels
