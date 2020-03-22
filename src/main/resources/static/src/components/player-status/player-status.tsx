import * as React from "react";

const PlayerStatus = (props : any) => {

    let callback = props.callback;

    function handleClick(e : any) {
        e.preventDefault();
        callback();
    }

    return (
        <div>
            <div>Идентификатор = {props.playerData.id}</div>
            <div>Имя = {props.playerData.name}</div>
            <div>Деньги = {props.playerData.money}</div>
            <button onClick={handleClick}>
                Обновить контент
            </button>
        </div>
    )
};

export default PlayerStatus;