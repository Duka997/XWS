import { INajvecaOcena } from './najvecaOcena';
import { INajviseKomentara } from './najviseKomentara';
import { INajviseKilometara } from './najviseKilometara';

export interface IStatistika {
    voziloSaNajvecomOcenomDTO: INajvecaOcena,
    voziloSaNajviseKomentaraDTO: INajviseKomentara,
    voziloSaNajvecomKilometrazomDTO: INajviseKilometara
}