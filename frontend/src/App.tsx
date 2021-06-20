import React from 'react';
import Channels from "./pages/channels/channels";
import 'bootstrap/dist/css/bootstrap.min.css';
import {Helmet} from "react-helmet";

const App = () => {
    return <>
        <Helmet>
            <meta charSet="utf-8"/>
            <title>TS-Dashboard</title>
        </Helmet>
        <Channels/>;
    </>
}

export default App;
