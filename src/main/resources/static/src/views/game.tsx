import * as React from 'react';
import * as ReactDOM from 'react-dom';
import GameHeader from "../components/game-header/game-header";
import CityCard from "../components/city-card/city-card";
import CityActions from "../components/city-actions/city-actions";
import PlayerStatus from "../components/player-status/player-status";

let page = (
    <div>
        <GameHeader/>
        <div>
            <CityCard/>
            <div>
                <CityActions/>
                <PlayerStatus/>
            </div>
        </div>
    </div>
);

let root = document.getElementById("react-game-root");
root && ReactDOM.render(page, root);
