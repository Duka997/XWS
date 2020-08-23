export interface User {
    id: number,
    name: string,
    surname: string,
    username: string,
    imeKompanije: string,
    isAdmin: boolean,
    enabled: boolean,
    address: string,
    poslovniID: string,
    email: string,
    roles: string[],
    password: string,
    phone: string
}