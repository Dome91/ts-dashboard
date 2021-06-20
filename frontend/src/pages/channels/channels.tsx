import {useChannels, useServers} from "../../hooks/team-speak-server-hooks";
import {Card, Col, ListGroup, Row} from "react-bootstrap";
import {TeamSpeakChannel} from "../../model/team-speak-server";
import {EmptyChannelCard, FullHeightContainer} from "./channels-styles";

type ChannelCardContentProps = {
    channel: TeamSpeakChannel,
    items: JSX.Element[]
}

const ChannelCardContent = ({channel, items}: ChannelCardContentProps) => {
    return <>
        <Card.Header>
            {channel.name}
        </Card.Header>
        <ListGroup variant={"flush"}>
            {items.length > 0 ? items : <ListGroup.Item>Channel is empty.</ListGroup.Item>}
        </ListGroup>
    </>
}

type ChannelCardProps = {
    channel: TeamSpeakChannel,
}

const ChannelCard = ({channel}: ChannelCardProps): JSX.Element => {
    const items = channel.users.map((user) => <ListGroup.Item>{user.name}</ListGroup.Item>)
    return <>
        {items.length === 0
            ? <EmptyChannelCard className={"m-2"}><ChannelCardContent channel={channel} items={items}/></EmptyChannelCard>
            : <Card className={"m-2"}><ChannelCardContent channel={channel} items={items}/></Card>}
    </>
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
