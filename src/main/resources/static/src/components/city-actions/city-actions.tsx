import * as React from "react";
import StoreEntry from "../store-entry/store-entry";

const CityActions = (props : any) => {
    return (
        <div>
            There will be actions
            <StoreEntry
                entryId="1"
                entryName="Маски"
                entryCost="100"
                onAction={props.onStoreAction}
            />
            <StoreEntry
                entryId="2"
                entryName="Вакцины"
                entryCost="200"
                onAction={props.onStoreAction}
            />
        </div>
    )
};

export default CityActions;