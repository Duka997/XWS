import { MarkaAutomobila } from './markaAutomobila';
import { TipGoriva } from './fuelType';
import { KlasaAutomobila } from './carClass';
import { TipMjenjaca } from './gearboxType';
import { Slika } from './image';

  

export class Vozilo {
    constructor(
        public id: number,
        public  cijena: number,
        public  kilometraza: number,
        public  mozePreciKM: number,
        public  brSjedistaZaDjecu: number,
        public  markaAutomobila: MarkaAutomobila,
        public  tipGoriva: TipGoriva,
        public  tipMjenjaca: TipMjenjaca,
        public  klasaAutomobila: KlasaAutomobila,
        public  imaAndroid: boolean,
        public  coliisionDamageWavier: boolean,
        public  ocjena: number,
        public  slike: Slika[],
        public bundle: boolean
    ){}
}