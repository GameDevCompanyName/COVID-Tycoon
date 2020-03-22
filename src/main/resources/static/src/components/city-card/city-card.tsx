// @ts-ignore
import * as React from "react";
import "./city-card.css";
import {useState} from "react";

const CityCard = (props : any) => {

    return (
        <div>
            <div className="city-card__photo-wrapper">
                City Photo
            </div>
            <div>
                {props.cityText}
            </div>
        </div>
    )
};

export default CityCard;