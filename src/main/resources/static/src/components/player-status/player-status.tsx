import * as React from "react";

const PlayerStatus = (props : any) => {
    return (
        <div>
            Player Status
            <button onClick={props.callback}>
                Обновить контент
            </button>
        </div>
    )
};

export default PlayerStatus;