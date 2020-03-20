import {useState} from "react";
import {useEffect} from "react";
import * as React from "react";
import "./login-form.css";

const LoginForm = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [isButtonDisabled, setIsButtonDisabled] = useState(true);

    useEffect(() => {
        if (username.trim().length == 0 && password.trim().length == 0) {
            setIsButtonDisabled(true);
        } else {
            setIsButtonDisabled(false);
        }
    }, [username, password]);


    return (
        <div className="login-form__form-wrapper">
            <form action="/login" className="login-form__form" method="POST">
                <input
                    className="login-form__form__username
                                login-form__input
                                common-styles__input"
                    name="username"
                    id="username"
                    placeholder="Имя"
                    onChange={(e) => setUsername(e.target.value)}
                />
                <input
                    className="login-form__form__password
                                login-form__input
                                common-styles__input"
                    name="password"
                    type="password"
                    id="password"
                    placeholder="Пароль"
                    onChange={(e) => setPassword(e.target.value)}
                />
                <input
                    className="login-form__form__submit
                                login-form__button
                                common-styles__button
                                common-styles__submit"
                    type="submit"
                    disabled={isButtonDisabled}
                />
            </form>
        </div>
    );

};

export default LoginForm;
