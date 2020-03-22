import * as React from 'react';
import {game} from "../../common/game";
import StoreAction = game.StoreAction;

function StoreEntry(props : any) {

    function handleBuy(e : any) {
        e.preventDefault();
        props.onAction(props.entryId, StoreAction.Buy);
    }

    function handleSell(e : any) {
        e.preventDefault();
        props.onAction(props.entryId, StoreAction.Sell);
    }

    return (
        <div>
            <button onClick={handleSell}>-</button>
            <span>{props.entryId} - {props.entryName} ({props.entryCost})</span>
            <button onClick={handleBuy}>+</button>
        </div>
    )
}

export default StoreEntry;