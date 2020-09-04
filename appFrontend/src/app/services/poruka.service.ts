import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Poruka} from '../model/poruka';

@Injectable({
    providedIn: 'root'
})
export class PorukaService{
    _url = 'http://localhost:8099/api/poruka'; 
    _url1 = 'http://localhost:8099/api/poruka/primljene'; 
    
    constructor(private _http: HttpClient) { }

    getMojePoruke(Id:number):Observable<any>{
        return this._http.get<Poruka[]>(`${this._url1}/${Id}`);
    }

    posaljiPoruku(poruka: Poruka) {
        return this._http.post<any>(this._url, poruka);
     }
}