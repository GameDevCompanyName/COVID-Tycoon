import * as React from "react";
import StoreEntry from "../store-entry/store-entry";
import {Component} from "react";
import {game} from "../../common/game";
import StoreEntryInfo = game.StoreEntryInfo;

const CityActions = (props: any) => {

    let entries = new Array<JSX.Element>();
    props.storeInfo.forEach(function (entry: StoreEntryInfo) {
        entries.push(
            <StoreEntry
                key={entry.id} //we need it for some reason
                entryId={entry.id}
                entryName={entry.name}
                entryCost={entry.cost}
                onAction={props.onStoreAction}
            />
        );
    });

    return (
        <div>
            There will be actions
            {entries}
        </div>
    )
};

export default CityActions;