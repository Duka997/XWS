import { IRentRequest } from './IRequestRent';
import { IBundle } from './IBundle';


export interface ICart {
    requests: IRentRequest[],
    bundles: IBundle[]
}