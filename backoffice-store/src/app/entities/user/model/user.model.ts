export class User {
    id: number | undefined
    nick: string
    name: string
    surname: string
    phoneNumber: number
    email: string
    password: string

    constructor(
        id: number | undefined,
        nick: string,
        name: string,
        surname: string,
        phoneNumber: number,
        email: string,
        password: string
    ) {
        this.id = id
        this.nick = nick
        this.name = name
        this.surname = surname
        this.phoneNumber = phoneNumber
        this.email = email
        this.password = password
    }
}