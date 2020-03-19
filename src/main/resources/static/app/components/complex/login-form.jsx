import React from "react";
import {Button, Form, Input} from "antd";
import "antd/es/button/style/index.css";
import "antd/es/form/style/index.css";
import "antd/es/input/style/index.css";
import "./login-form.css"
import createPostRequest from "../../js/common/request";

const layout = {
    labelCol: {span: 8},
    wrapperCol: {span: 16},
};
const tailLayout = {
    wrapperCol: {offset: 8, span: 16},
};

function sendData() {

}

function LoginForm({ onChange, fields }) {

        return <Form
            fields={fields}
            onFieldsChange={(changedFields, allFields) => {
                onChange(allFields);
            }}
            size="large"
            {...layout}
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

            <div className="login-form__submit-button-wrapper">
                <Form.Item {...tailLayout}>
                    <Button type="primary" htmlType="submit">
                        Submit
                    </Button>
                </Form.Item>
            </div>

        </Form>
}

export default LoginForm;
