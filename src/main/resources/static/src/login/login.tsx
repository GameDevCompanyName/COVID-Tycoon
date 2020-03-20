import * as ReactDOM from 'react-dom';
import * as React from 'react';
import LoginForm from "../components/login-form/login-form";
import LinkButton from "../components/common/LinkButton";

let page = (
    <div>
        <LoginForm />
        <LinkButton href="/registration" text="Создать пользователя"/>
    </div>
);

let root = document.getElementById('react-login-root');
root && ReactDOM.render(page, root);
