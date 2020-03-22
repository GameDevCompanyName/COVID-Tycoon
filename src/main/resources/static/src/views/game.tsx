import * as React from 'react';
import {useState} from 'react';
import * as ReactDOM from 'react-dom';

import GameHeader from "../components/game-header/game-header";
import CityCard from "../components/city-card/city-card";
import CityActions from "../components/city-actions/city-actions";
import PlayerStatus from "../components/player-status/player-status";
import LinkButton from "../components/common/LinkButton";

import {game} from "../common/game";
import {http} from "../common/http"
import StoreAction = game.StoreAction;
import getRequest = http.getRequest;
import PlayerData = game.PlayerData;
import contentOfGetRequest = http.contentOfGetRequest;
import getEntity = http.getEntity;

const Game = () => {

    const [text, setText] = useState('...');
    const [playerData, setPlayerData] = useState(PlayerData.initialState);

    function showToast(text: any) {
        alert(text.toString());
    }

    onload = () => {
        // getEntity("/game/player", playerDataUpdated);
    };

    function playerDataUpdated(playerData : PlayerData){
        setPlayerData(playerData);
    }

    function storeAction(entryId: string, action: StoreAction) {
        alert(entryId + action.toString());
    }

    function updatePlayerInfo() {
        getEntity("/game/player", playerDataUpdated);
    }

    return (
        <div>
            <GameHeader/>
            <div>
                <CityCard cityText={text}/>
                <div>
                    <CityActions onStoreAction={storeAction}/>
                    <PlayerStatus
                        callback={updatePlayerInfo}
                        playerData={playerData}/>
                </div>
            </div>
            <LinkButton href="/logout" text="Выйти"/>
        </div>
    );
};


let root = document.getElementById("react-game-root");
root && ReactDOM.render(<Game/>, root);
