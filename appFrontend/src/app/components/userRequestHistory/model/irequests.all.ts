import { IRentRequestHistory } from './IRequestRent';

export interface IAllRequestsHistory {
    all: IRentRequestHistory[];
    pending: IRentRequestHistory[];
    paid: IRentRequestHistory[];
    finished: IRentRequestHistory[];
}