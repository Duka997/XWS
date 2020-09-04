import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Vozilo } from 'src/app/model/car';




@Injectable({
    providedIn: 'root'
})
export class DodajVoziloService{
    _url = 'http://localhost:8099/api/vozilo/dodaj';
    _url2 = 'http://localhost:8099/api/vozilo/get';
    _url3 = 'http://localhost:8099/api/oglasi/allAds';
   

    constructor(private _http: HttpClient) { }

   dodajVozilo(vozilo: Vozilo, agentId: any) {
       return  this._http.post<any>('http://localhost:8099/api/vozilo/dodaj/'+agentId, vozilo);
    }

    getVozila():Observable<any>{
        return this._http.get<Vozilo[]>(this._url2);
    }
  //  getOglasi():Observable<any>{
       // return this._http.get<Oglas[]>(this._url3);
   // }
    
}