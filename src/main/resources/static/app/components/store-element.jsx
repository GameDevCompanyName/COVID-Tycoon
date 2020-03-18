import React from 'react';
import ReactDom from 'react-dom';

class StoreElement extends React.Component {

    render() {
        return (
            <div className="store-element">
                <button className="quantity-button quantity-button__minus">-</button>
                <label className="store-element__label">Stone</label>
                <label className="store-element__cost">(2000.00)</label>
                <button className="quantity-button quantity-button__plus">+</button>
                <label className="store-element__current-quantity">25</label>
            </div>
        )
    }

}

export default StoreElement;

