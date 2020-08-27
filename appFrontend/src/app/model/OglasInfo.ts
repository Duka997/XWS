import { Vozilo } from './car';
import { Cjenovnik } from './Cjenovnik';

export interface OglasInfo {
    id: number,
    naziv: string,
    pages: number,
    mjestoPreuzimanja: string,
    od: Date,
    doo: Date,
    dozvoljenaKilometraza: number,
    vozilo: Vozilo,
    cjenovnik: Cjenovnik
}