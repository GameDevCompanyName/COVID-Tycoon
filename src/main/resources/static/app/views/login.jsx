import React, { useState } from "react";
import ReactDom from "react-dom";
import "./login.css"
import "antd/es/layout/style/index.css";
import LoginForm from "../components/complex/login-form";


function Login() {

        const [fields, setFields] = useState([
            {
                name: ['username'],
                value: ''
            },
            {
                name: ['password'],
                value: ''
            }
        ]);

        return <div className="login-form-wrapper">
            <h1 className="login-header-text">{fields.toString()}</h1>
            <LoginForm
                fields={fields}
                onChange={newFields => {
                    setFields(newFields);
                    alert(fields);
                }}
            />
        </div>
}



let root = document.getElementById('login-react');
root && ReactDom.render(<Login/>, document.getElementById('login-react'));
