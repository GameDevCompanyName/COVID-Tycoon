import * as React from 'react';

const LinkButton = (props : any) => {
    return (
        <form action={props.href}>
            <button type="submit">
                {props.text}
            </button>
        </form>
    )
};

export default LinkButton;
