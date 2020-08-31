import { Oglas } from 'src/app/model/add';

export interface IRentRequestHistory {
    id: number,
    mjestoPreuzimanja: string,
    od: Date,
    doo: Date,
    oglas: Oglas,
    bundleId: number
}