import * as React from 'react';
import {useEffect, useState} from 'react';
import * as ReactDOM from 'react-dom';

import "./game.css";

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
import CityData = game.CityData;
import StoreEntryInfo = game.StoreEntryInfo;

const Game = () => {

    const [text, setText] = useState('...');
    const [playerData, setPlayerData] = useState(PlayerData.initialState);
    const [cityData, setCityData] = useState(CityData.initialState);
    const [storeInfo, setStoreInfo] = useState(new Array<StoreEntryInfo>());

    useEffect(() => {
        let list = new Array<StoreEntryInfo>();
        list.push(
            new StoreEntryInfo(1, "Маски", cityData.costMask)
        );
        list.push(
            new StoreEntryInfo(2, "Вакцины", cityData.costVacc)
        );
        setStoreInfo(list);
    }, [cityData]);

    function showToast(text: any) {
        alert(text.toString());
    }

    function playerDataUpdated(playerData: PlayerData) {
        setPlayerData(playerData);
    }

    function storeAction(entryId: string, action: StoreAction) {
        alert(entryId + action.toString());
    }

    function cityDataUpdated(cityData: CityData) {
        setCityData(cityData);
    }

    function updateInfo() {
        getEntity("/game/player", playerDataUpdated);
        getEntity("/game/city", cityDataUpdated);
    }

    return (
        <div className="game__main-wrapper">
            <div className="game__header">
                <GameHeader />
            </div>
            <div className="game__main-content">
                <div className="game__main-content__city-card">
                    <CityCard cityData={cityData}/>
                </div>
                <div className="game__main-content__interaction">
                    <div className="game__main-content__interaction__actions">
                        <CityActions
                            onStoreAction={storeAction}
                            storeInfo={storeInfo}
                        />
                    </div>
                    <div className="game__main-content__interaction__player">
                        <PlayerStatus
                            callback={updateInfo}
                            playerData={playerData}
                        />
                    </div>
                </div>
            </div>
            <div className="game__footer">
                <LinkButton href="/logout" text="Выйти"/>
            </div>
        </div>
    );
};

let root = document.getElementById("react-game-root");
root && ReactDOM.render(<Game/>, root);
