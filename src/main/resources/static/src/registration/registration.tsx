import * as ReactDOM from 'react-dom';
import * as React from 'react';
import RegistrationForm from "../components/register-form/registration-form";
import LinkButton from "../components/common/LinkButton";

let page = (
    <div>
        <RegistrationForm />
        <LinkButton href="/login" text="Войти"/>
    </div>
);

let root = document.getElementById('react-registration-root');
root && ReactDOM.render(page, root);
