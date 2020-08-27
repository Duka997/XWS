import { MarkaAutomobila } from "./markaAutomobila";
import { TipGoriva } from './fuelType';
import { TipMjenjaca } from './gearboxType';
import { KlasaAutomobila } from './carClass';

export interface Pretraga {
    mjestoPreuzimanja: string;
    od: Date;
    doo: Date;
    marka: MarkaAutomobila;
    gorivo: TipGoriva;
    mjenjac: TipMjenjaca;
    klasa: KlasaAutomobila;
    cijenaOd: number;
    cijenaDo: number;
    kilometrazaOd: number;
    kilometrazaDo: number;
    kilometrazaDozvoljena: number;
    cdw: boolean;
    brojDjecijihMjesta: number;
}