import * as React from 'react';
import * as ReactDOM from 'react-dom';
import httpImported = require("../common/http");


import GameHeader from "../components/game-header/game-header";
import CityCard from "../components/city-card/city-card";
import CityActions from "../components/city-actions/city-actions";
import PlayerStatus from "../components/player-status/player-status";
import {useEffect, useState} from "react";
import LinkButton from "../components/common/LinkButton";

const Game = () => {

    const [text, setText] = useState('...');

    let page = (
        <div>
            <GameHeader/>
            <div>
                <CityCard cityText={text}/>
                <div>
                    <CityActions/>
                    <PlayerStatus callback={updateContent}/>
                </div>
            </div>
            <LinkButton href="/logout" text="Выйти"/>
        </div>
    );

    function updateContent(){
        setText("FLEXTIME");
    }

    return page;
};



let root = document.getElementById("react-game-root");
root && ReactDOM.render(<Game/>, root);
