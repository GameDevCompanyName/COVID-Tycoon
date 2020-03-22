export module http {

    export function getRequest(url: string): Promise<any> {
        return new Promise<any>(
            function (resolve, reject) {
                const request = new XMLHttpRequest();
                request.onload = function () {
                    if (this.status === 200) {
                        resolve(this.response);
                    } else {
                        reject(new Error(this.statusText));
                    }
                };
                request.onerror = function () {
                    reject(new Error('XMLHttpRequest Error: ' + this.statusText));
                };
                request.open('GET', url);
                request.send();
            }
        );
    }

    export function contentOfGetRequest(url: string, handler: Function) : void {
        getRequest(url)
            .then(response => {
                let responseContent = JSON.parse(response);
                let responseResult = new Response(
                    responseContent.status,
                    responseContent.entity
                );
                returnEntity(responseResult, handler);
            })
            .catch(error => {
                alert(error);
            });
    }

    export function getEntity(url: string, handler: Function) : void {
        contentOfGetRequest(url, handler);
    }

    function returnEntity(response: Response, handler: Function) : void {
        if (response.status == 200)
        handler(response.entity);
    }

    class Response {

        constructor(status: number, entity: Object){
            this.status = status;
            this.entity = entity;
        }

        status: number;
        entity: Object;
    }

}
