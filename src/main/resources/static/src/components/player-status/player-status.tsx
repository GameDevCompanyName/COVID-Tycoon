import * as React from "react";

const PlayerStatus = (props : any) => {

    let callback = props.callback;

    function handleClick(e : any) {
        e.preventDefault();
        callback();
    }

    return (
        <div>
            Player Status
            <button onClick={handleClick}>
                Обновить контент
            </button>
        </div>
    )
};

export default PlayerStatus;