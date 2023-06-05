import { Item } from "../../item/model/item.model"

export class User {
    id: number | undefined
    nick: string
    name: string
    surname: string
    phoneNumber: number
    email: string
    password: string
    favourites: Item[]

    constructor(
        id: number | undefined,
        nick: string,
        name: string,
        surname: string,
        phoneNumber: number,
        email: string,
        password: string,
        favourites: Item[]
    ) {
        this.id = id
        this.nick = nick
        this.name = name
        this.surname = surname
        this.phoneNumber = phoneNumber
        this.email = email
        this.password = password
        this.favourites = favourites
    }
}