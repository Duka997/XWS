import { Vozilo } from './car';
import { User } from './user';

export class Oglas {
    constructor(
        public id: number,
        public dostupan: Boolean,
        public vozilo: Vozilo,
        public mjestoPreuzimanja: string,
        public od : Date,
        public doo : Date,
        public userId : number,
        public cjenovnikID : number,
        public bundleId: number,
        public adId: number,
        public user: User
    ){}
}