import { Vozilo } from './car';

export interface Occupied {
    id: number,
    doo: Date,
    od: Date,
    voziloId: number,
    vozilo: Vozilo,
    oglasId: number[]
}