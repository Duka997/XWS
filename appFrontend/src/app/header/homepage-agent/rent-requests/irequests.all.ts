import { IRentRequest } from '../../homepageUser/userCart/IRequestRent';

export interface IAllRequests {
    all: IRentRequest[];
    pending: IRentRequest[];
    paid: IRentRequest[];
    finished: IRentRequest[];
}