export module game {
    export enum StoreAction {
        Buy,
        Sell,
        BuyAll,
        SellAll
    }

    export class PlayerData {

        constructor(id: number, name: string, money: number, cityId: number, quantityMask: number, quantityVacc: number, userId: number) {
            this.id = id;
            this.name = name;
            this.money = money;
            this.cityId = cityId;
            this.quantityMask = quantityMask;
            this.quantityVacc = quantityVacc;
            this.userId = userId;
        }

        id: number;
        name: string;
        money: number;
        cityId: number;
        quantityMask: number;
        quantityVacc: number;
        userId: number;

        static initialState: PlayerData = new PlayerData(
            -1,
            "Default",
            0,
            -1,
            0,
            0,
            -1
        );

    }
}
