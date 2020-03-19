import React from "react";
import {Button, Form, Input} from "antd";
import "antd/es/button/style/index.css";
import "antd/es/form/style/index.css";
import "antd/es/input/style/index.css";

class LoginForm extends React.Component {
    render() {
        return <Form
            name="login"
            size="large"
            //onFinish =
            //onFinishFailed =
        >
            <Form.Item
                label="Имя"
                name="username"
                rules={[
                    {
                        required: true,
                        message: "Введите имя пользователя!"
                    },
                    {
                        max: 20,
                        message: "Слишком длинное имя!"
                    },
                    {
                        min: 5,
                        message: "Слишком короткое имя!"
                    },
                    {
                        whiteSpace: false,
                        message: "Пробелов не существует."
                    }
                ]}
            >
                <Input/>
            </Form.Item>

            <Form.Item
                label="Пароль"
                name="password"
                rules={[
                    {
                        required: true,
                        message: "Введите пароль!"
                    }
                ]}
            >
                <Input.Password/>
            </Form.Item>

            <Form.Item>
                <Button type="primary" htmlType="submit">
                    Submit
                </Button>
            </Form.Item>

        </Form>
    }
}

export default LoginForm;
