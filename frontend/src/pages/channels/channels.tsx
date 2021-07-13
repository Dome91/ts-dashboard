import {useChannels, useServers} from "../../hooks/team-speak-server-hooks";
import {Card, Col, Dropdown, ListGroup, Row} from "react-bootstrap";
import {TeamSpeakChannel} from "../../model/team-speak-server";
import {ChannelsHeader, EmptyChannelCard, FullHeightContainer} from "./channels-styles";
import {ChannelsSortedBy} from "../../clients/team-speak-server-client";
import {useState} from "react";

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
    const items = channel.users.map((user, index) => <ListGroup.Item key={index}>{user.name}</ListGroup.Item>)
    return <>
        {items.length === 0
            ? <EmptyChannelCard className={"m-2"}><ChannelCardContent channel={channel} items={items}/></EmptyChannelCard>
            : <Card className={"m-2"}><ChannelCardContent channel={channel} items={items}/></Card>}
    </>
}

type ChannelCardsProps = {
    serverId: string
    sortedBy: ChannelsSortedBy
}

const ChannelCards = ({serverId, sortedBy}: ChannelCardsProps): JSX.Element => {
    const channels = useChannels(serverId, sortedBy)
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
    const [sortedBy, setSortedBy] = useState(ChannelsSortedBy.USERS_DESC)
    return <FullHeightContainer fluid className={"d-flex justify-content-center align-items-center flex-column"}>
        <ChannelsHeader>
            <Dropdown>
                <Dropdown.Toggle variant="success" id="dropdown-basic">
                    Sort By
                </Dropdown.Toggle>

                <Dropdown.Menu>
                    <Dropdown.Item onClick={() => setSortedBy(ChannelsSortedBy.USERS_ASC)}>Users (Asc)</Dropdown.Item>
                    <Dropdown.Item onClick={() => setSortedBy(ChannelsSortedBy.USERS_DESC)}>Users (Desc)</Dropdown.Item>
                </Dropdown.Menu>
            </Dropdown>
        </ChannelsHeader>
        <Row>
            {servers.length > 0 ? <ChannelCards serverId={servers[0].id} sortedBy={sortedBy}/> : <div/>}
        </Row>
    </FullHeightContainer>
}

export default Channels
