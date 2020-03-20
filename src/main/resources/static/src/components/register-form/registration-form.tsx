import {useState} from "react";
import {useEffect} from "react";
import * as React from "react";
import "./registration-form.css";

const RegistrationForm = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [confPassword, setConfPassword] = useState('');
    const [isButtonDisabled, setIsButtonDisabled] = useState(true);

    useEffect(() => {
        if (username.trim().length == 0
            || password.trim().length == 0
            || password !== confPassword
        ) {
            setIsButtonDisabled(true);
        } else {
            setIsButtonDisabled(false);
        }
    }, [username, password, confPassword]);


    return (
        <div className="registration-form__form-wrapper">
            <form action="/registration" className="registration-form__form" method="POST">
                <input
                    className="registration-form__form__username
                                registration-form__input
                                common-styles__input"
                    name="userName"
                    placeholder="Имя"
                    onChange={(e) => setUsername(e.target.value)}
                />
                <input
                    className="registration-form__form__password
                                registration-form__input
                                common-styles__input"
                    name="pass"
                    type="password"
                    placeholder="Пароль"
                    onChange={(e) => setPassword(e.target.value)}
                />
                <input
                    className="registration-form__form__password
                                registration-form__input
                                common-styles__input"
                    name="passConfirm"
                    type="password"
                    placeholder="Снова пароль"
                    onChange={(e) => setConfPassword(e.target.value)}
                />
                <input
                    className="registration-form__form__submit
                                registration-form__button
                                common-styles__button
                                common-styles__submit"
                    type="submit"
                    disabled={isButtonDisabled}
                />
            </form>
        </div>
    );

};

export default RegistrationForm;
