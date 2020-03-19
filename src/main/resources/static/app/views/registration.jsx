import React from "react";
import ReactDom from "react-dom";

class Registration extends React.Component {

    render() {
        return <div>THIS IS REGISTRATION REACT</div>
    }
}

let root = document.getElementById('registration-react');
root && ReactDom.render(<Registration />, document.getElementById('registration-react'));
