import { IRentRequest } from 'src/app/components/userCart/IRequestRent';

export interface IAllRequests {
    all: IRentRequest[];
    pending: IRentRequest[];
    paid: IRentRequest[];
    finished: IRentRequest[];
}