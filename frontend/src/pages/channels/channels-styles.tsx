import styled from "styled-components";
import {Card, Container} from "react-bootstrap";

export const FullHeightContainer = styled(Container)`
  min-height: 100vh;
`

export const ChannelsHeader = styled.div`
  display: flex;
  justify-content: end;
  margin-bottom: 2rem;
  width: 100%;
`

export const EmptyChannelCard = styled(Card)`
  opacity: 30%;
`
