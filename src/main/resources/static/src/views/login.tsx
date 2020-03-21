import * as ReactDOM from 'react-dom';
import * as React from 'react';
import LinkButton from "../components/common/LinkButton";
import LoginForm from "../components/login-form/login-form";

let page = (
    <div>
        <LoginForm />
        <LinkButton href="/registration" text="Создать пользователя"/>
    </div>
);

let root = document.getElementById('react-login-root');
root && ReactDOM.render(page, root);
