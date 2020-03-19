import React from "react";
import ReactDom from "react-dom";
import {Layout} from "antd";
import "antd/es/layout/style/index.css";

import LoginForm from "../components/complex/login-form";

class Login extends React.Component {

    render() {
        return <div className="login-form-wrapper">
            <Layout>
                <Layout>
                    <h1>Добро пожаловать в игру</h1>
                </Layout>
                <Layout>
                    <LoginForm/>
                </Layout>
            </Layout>
        </div>
    }
}

let root = document.getElementById('login-react');
root && ReactDom.render(<Login/>, document.getElementById('login-react'));
