import { Vozilo } from './car';

export class Oglas {
    constructor(
        public id: number,
        public dostupan: Boolean,
        public vozilo: Vozilo,
        public mjestoPreuzimanja: string,
        public od : Date,
        public doo : Date,
        public userId : number,
        public cjenovnikID : number
    ){}
}