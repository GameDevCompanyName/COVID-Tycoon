import React from "react";
import ReactDom from "react-dom";
import {Layout} from "antd";
import "../../styles/login/login.css"
import "antd/es/layout/style/index.css";
import LoginForm from "../components/complex/login-form";

class Login extends React.Component {

    render() {
        return <div className="login-form-wrapper">
            <h1 className="login-header-text">COVID-Tycoon</h1>
            <LoginForm/>
        </div>
    }
}

let root = document.getElementById('login-react');
root && ReactDom.render(<Login/>, document.getElementById('login-react'));
