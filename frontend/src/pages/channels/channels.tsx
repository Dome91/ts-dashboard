import {useChannels, useServers} from "../../hooks/team-speak-server-hooks";
import {Card, Col, ListGroup, Row} from "react-bootstrap";
import {TeamSpeakChannel, TeamSpeakUser} from "../../model/team-speak-server";
import {FullHeightContainer} from "./channels-styles";

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
    return <Card className={"m-2"}>
        <Card.Header>
            {channel.name}
        </Card.Header>
        <ListGroup variant={"flush"}>
            {userEntries.length > 0 ? userEntries : <ListGroup.Item>Channel is empty.</ListGroup.Item>}
        </ListGroup>
    </Card>
}

type ChannelCardsProps = {
    serverId: string
}

const ChannelCards = ({serverId}: ChannelCardsProps): JSX.Element => {
    const channels = useChannels(serverId)
    const channelCards = channels.map((channel, index) =>
        <Col lg={3} md={4} sm={6} xs={12} key={index}>
            <ChannelCard channel={channel}/>
        </Col>)

    return <>
        {channelCards}
    </>
}

const Channels = () => {
    const servers = useServers()
    return <FullHeightContainer fluid className={"d-flex justify-content-center align-items-center"}>
        <Row>
            {servers.length > 0 ? <ChannelCards serverId={servers[0].id}/> : <div/>}
        </Row>
    </FullHeightContainer>
}

export default Channels
