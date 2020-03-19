import React from "react";
import ReactDom from "react-dom";

class Index extends React.Component {

    render() {
        return <div>THIS IS Index REACT</div>
    }
}

let root = document.getElementById('index-react');
root && ReactDom.render(<Index />, document.getElementById('index-react'));
