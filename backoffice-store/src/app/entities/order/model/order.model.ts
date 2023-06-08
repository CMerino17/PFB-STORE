import { Item } from "../../item/model/item.model"

export class Order {
    id: number | undefined
    date: string
    userId: number
    items: Item[]

    constructor(
        id: number | undefined,
        date: string,
        userId: number,
        items: Item[]
    ) {
        this.id = id
        this.date = date
        this.userId = userId
        this.items = items
    }
}