// @ts-ignore
import * as React from "react";
import "./city-card.css";
import {useState} from "react";

const CityCard = (props : any) => {

    return (
        <div className="city-card__main">
            <div className="city-card__photo">
                City Photo
            </div>
            <div className="city-card__info">
                <h1>Город</h1>
                <div>Идентификатор = {props.cityData.id}</div>
                <div>Название = {props.cityData.name}</div>
                <div>{props.cityData.players.toString()}</div>
                {/*<PlayerList players={props.cityData.players}/>*/}
            </div>
        </div>
    )
};

export default CityCard;